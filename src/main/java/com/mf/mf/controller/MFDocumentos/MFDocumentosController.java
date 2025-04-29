package com.mf.mf.controller.MFDocumentos;

import com.mf.mf.dto.anulacion.MFAnexoAnulacionDTO;
import com.mf.mf.dto.anulacion.MFGuardarAnexoSolicitudDTO;
import com.mf.mf.dto.anulacion.MFSolicitudAnulacionDTO;
import com.mf.mf.model.MFDocumentos;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.anulacion.MFAnexosAnulacion;
import com.mf.mf.model.anulacion.MFSolicitudAnulacion;
import com.mf.mf.projection.GetMFDocumentosProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import com.mf.mf.repository.MFAnulacion.MFSolicitudAnulacionRepository;
import com.mf.mf.repository.MFDocumentos.MFDocumentosRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.services.MFDocumentos.MFDocumentosServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentos")
@Tag(name = "Documentos", description = "Guardar documentos en general")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MFDocumentosController {

    private final MFDocumentosServices documentosService;
    private final MFDocumentosRepository mfDocumentosRepository;
    private final MFSolicitudAnulacionRepository mfSolicitudAnulacionRepository;
    private final MFHeredadosRepository mfHeredadosRepository;

    // Clase auxiliar para recibir la lista de documentos
    public static class DocumentoRequest {
        private List<MFDocumentos> documentos;

        public List<MFDocumentos> getDocumentos() {
            return documentos;
        }

        public void setDocumentos(List<MFDocumentos> documentos) {
            this.documentos = documentos;
        }
    }

    //guardar docs
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarDocumento(@RequestBody MFDocumentos documento) {


        // Verificar si el idHeredado tiene una solicitud de anulación aprobada
        boolean hasApprovedAnulation = mfSolicitudAnulacionRepository.existsByIdHeredadoAndEstadoSolicitud(
                documento.getIdHeredado(), "Aprobado");

        if (hasApprovedAnulation) {
            System.out.println(documento.getIdHeredado());
            // Si existe una solicitud de anulación aprobada, actualizar el estado del registro anterior
            mfDocumentosRepository.changeEstado(documento.getIdHeredado());

        }

        MFDocumentos docGuardado = documentosService.guardarDocumento(documento);


        return ResponseEntity.ok(docGuardado);
    }

    //Guardar documentos reportes intermedios
    @PostMapping("/guardar-multiples")
    public ResponseEntity<?> guardarMultiplesDocumentos(@RequestBody DocumentoRequest request) {

        if (request.getDocumentos() == null || request.getDocumentos().isEmpty()) {
            return ResponseEntity.badRequest().body("No se recibieron documentos para guardar.");
        }

        // Guardar todos los documentos
        for (MFDocumentos documento : request.getDocumentos()) {
            documentosService.guardarDocumento(documento, true);
        }



        return ResponseEntity.ok("Documentos guardados exitosamente.");
    }

    //get by nit
    @GetMapping("/documentosCargados")
    public ResponseEntity<List<GetMFDocumentosProjection>> findByNIT(@RequestParam Integer nit) {
        try {
            List<GetMFDocumentosProjection> docs = mfDocumentosRepository.findByNITProjection(nit);
            return ResponseEntity.ok(docs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Obtener historial -- rol misional --
    @GetMapping("/comparativo-documentos")
    public List<GetMFDocumentosProjection> compararDocumentos(@RequestParam Integer idHeredado) {
        List<GetMFDocumentosProjection> registros = mfDocumentosRepository.findByHeredado1(idHeredado);

        if (registros == null || registros.size() < 2) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        return registros; // ✅ retorna una lista válida
    }

}
