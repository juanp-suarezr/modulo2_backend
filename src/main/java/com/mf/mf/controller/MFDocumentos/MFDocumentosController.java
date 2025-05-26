package com.mf.mf.controller.MFDocumentos;

import com.mf.mf.model.MFDocumentos;
import com.mf.mf.projection.GetMFDocumentosProjection;
import com.mf.mf.repository.MFAnulacion.MFSolicitudAnulacionRepository;
import com.mf.mf.repository.MFDocumentos.MFDocumentosRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.services.MFDocumentos.MFDocumentosServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        // Getters y setters
        @Setter
        @Getter
        private List<String> paths;
        @Getter
        private Integer nit;
        @Setter
        private Long idHeredado;

        public void setNit(String nit) {
            this.nit = Integer.valueOf(nit);
        }

        public Integer getIdHeredado() {
            return Math.toIntExact(idHeredado);
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
    public ResponseEntity<String> guardarMultiplesDocumentos(@RequestBody DocumentoRequest request) {

        if (request.getPaths() == null || request.getPaths().isEmpty()) {
            return ResponseEntity.badRequest().body("No se recibieron rutas para guardar.");
        }

        for (String path : request.getPaths()) {
            MFDocumentos documento = new MFDocumentos();
            documento.setLink(path); // O el campo real si no se llama 'link'
            documento.setNit(request.getNit()); // Solo si MFDocumentos tiene este campo
            documento.setIdHeredado(request.getIdHeredado());
            documento.setEstado(true);

            documentosService.guardarMultiDocumento(documento, true);
        }

        return ResponseEntity.ok("Documentos guardados exitosamente.");
    }

    //Actualizar documentos reportes intermedios
    @PostMapping("/actualizar-multiples")
    public ResponseEntity<String> actualizarMultiplesDocumentos(@RequestBody DocumentoRequest request) {

        if (request.getPaths() == null || request.getPaths().isEmpty()) {
            return ResponseEntity.badRequest().body("No se recibieron rutas para guardar.");
        }

        mfDocumentosRepository.deleteByIdHeredado(request.getIdHeredado());

        for (String path : request.getPaths()) {
            MFDocumentos documento = new MFDocumentos();
            documento.setLink(path); // O el campo real si no se llama 'link'
            documento.setNit(request.getNit()); // Solo si MFDocumentos tiene este campo
            documento.setIdHeredado(request.getIdHeredado());
            documento.setEstado(true);

            documentosService.guardarMultiDocumento(documento, true);
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

    //get by heredado
    @GetMapping("/documentosCargados1")
    public ResponseEntity<List<GetMFDocumentosProjection>> findByHeredado(@RequestParam Integer idHeredado) {
        try {
            List<GetMFDocumentosProjection> docs = mfDocumentosRepository.findByIdProjection(idHeredado);
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
