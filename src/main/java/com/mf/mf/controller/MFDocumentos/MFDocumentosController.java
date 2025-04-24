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
    public Map<String, Object> compararDocumentos(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }

        // Obtener los registros correspondientes al NIT e ID heredado
        // Intentar obtener los registros

        List<GetMFDocumentosProjection> registros = mfDocumentosRepository.findByHeredado1(idHeredado);


        if (registros == null || registros.size() < 2) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        // Filtrar los registros por estado
        GetMFDocumentosProjection registroAntiguo = registros.stream()
                .filter(r -> !r.getEstado()) // Estado false
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro antiguo (estado = false)."));

        GetMFDocumentosProjection registroActualizado = registros.stream()
                .filter(r -> r.getEstado()) // Estado true
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro actualizado (estado = true)."));

        // Comparar los registros automáticamente usando reflexión
        Map<String, Object> cambios = new HashMap<>();
        try {
            for (var method : GetMFIdentificacionVigiladoProjection.class.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) { // Solo métodos "get"
                    String fieldName = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4); // Obtener el nombre del campo
                    Object valorAntiguo = method.invoke(registroAntiguo);
                    Object valorActualizado = method.invoke(registroActualizado);

                    if (valorAntiguo != null && !valorAntiguo.equals(valorActualizado)) {
                        cambios.put(fieldName, Map.of("antiguo", valorAntiguo, "actualizado", valorActualizado));
                    } else if (valorAntiguo == null && valorActualizado != null) {
                        cambios.put(fieldName, Map.of("antiguo", null, "actualizado", valorActualizado));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comparar los registros.", e);
        }

        return cambios; // Devuelve el mapa con los cambios al frontend
    }
}
