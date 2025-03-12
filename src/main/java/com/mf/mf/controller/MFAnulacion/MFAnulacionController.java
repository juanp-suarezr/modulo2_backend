package com.mf.mf.controller.MFAnulacion;

import com.mf.mf.dto.anulacion.MFAnexoAnulacionDTO;
import com.mf.mf.dto.anulacion.MFGuardarAnexoSolicitudDTO;
import com.mf.mf.dto.anulacion.MFSolicitudAnulacionDTO;
import com.mf.mf.dto.excel.MFAnexosDTO;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.anulacion.MFAnexosAnulacion;
import com.mf.mf.model.anulacion.MFSolicitudAnulacion;
import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.projection.MFAnulacion.GetMFAnexoAnulacionProjection;
import com.mf.mf.projection.MFAnulacion.GetMFSolicitudAnulacionProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosEntregasProjection;
import com.mf.mf.repository.MFAnulacion.MFAnexoAnulacionRepository;
import com.mf.mf.repository.MFAnulacion.MFSolicitudAnulacionRepository;
import com.mf.mf.repository.MFExcelRepository.MFAnexosRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
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
@RequestMapping("/api/anulacion")
@Tag(name = "Anulacion", description = "formulario de anulacion, vista misional y aprobacion o negacion de anulacion")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MFAnulacionController {

    private final MFAnexoAnulacionRepository mfAnexoAnulacionRepository;
    private final MFHeredadosRepository mfHeredadosRepository;
    private final MFSolicitudAnulacionRepository mfSolicitudAnulacionRepository;

    // Obtener anexos anulados por idHeredado
    @GetMapping("/byIDHeredado")
    public ResponseEntity<List<GetMFAnexoAnulacionProjection>> findByidAnulado(@RequestParam String idHeredado) {
        try {
            List<GetMFAnexoAnulacionProjection> anexos = mfAnexoAnulacionRepository.findAnexosByAnulado(Integer.valueOf(idHeredado));
            return ResponseEntity.ok(anexos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Guardar solicitud de anulacion y anexos anulacion
    @PostMapping("/guardar")
    @Transactional
    public ResponseEntity<?> guardarAnexoAnulacion(@RequestBody MFGuardarAnexoSolicitudDTO requestBody) {
        Map<String, String> response = new HashMap<>();
        try {
            // Obtener los datos de la solicitud y el anexo
            MFSolicitudAnulacionDTO requestBodySolicitud = requestBody.getSolicitud();
            MFAnexoAnulacionDTO requestBodyAnexo = requestBody.getAnexo();

            // Buscar el heredado
            Optional<MFHashHeredado> heredadoOpt = mfHeredadosRepository.findByIdHeredado(requestBodySolicitud.getIdHeredado());
            if (heredadoOpt.isEmpty()) {
                response.put("error", "No se encontró un heredado con el ID proporcionado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            MFHashHeredado heredado = heredadoOpt.get();

            // Guardar la solicitud de anulación (asumiendo que tienes una entidad `MFSolicitudAnulacion`)
            MFSolicitudAnulacion solicitud = new MFSolicitudAnulacion();
            solicitud.setIdHeredado(requestBodySolicitud.getIdHeredado());
            solicitud.setNombre(requestBodySolicitud.getNombre());
            solicitud.setRazonSocial(requestBodySolicitud.getRazonSocial());
            solicitud.setIdentificacion(requestBodySolicitud.getIdentificacion());
            solicitud.setEmail(requestBodySolicitud.getEmail());
            solicitud.setEmail1(requestBodySolicitud.getEmail1());
            solicitud.setPeticion(requestBodySolicitud.getPeticion());
            solicitud.setItemsModificados(requestBodySolicitud.getItemsModificados());
            solicitud.setEstadoSolicitud("Pendiente");
            solicitud.setEstado(true);
            solicitud.setFechaSolicitud(LocalDate.now());

            mfSolicitudAnulacionRepository.save(solicitud);

            // Guardar el anexo de anulación
            MFAnexosAnulacion anexo = new MFAnexosAnulacion();
            anexo.setIdAnulacion(requestBodyAnexo.getIdAnulacion());
            anexo.setNombre(requestBodyAnexo.getNombre());
            anexo.setDetalle(requestBodyAnexo.getDetalle());
            anexo.setPath(requestBodyAnexo.getPath());
            anexo.setEstado(true);

            mfHeredadosRepository.actualizarEstadoEntrega(requestBodySolicitud.getIdHeredado(), 461);
            mfAnexoAnulacionRepository.save(anexo);

            response.put("mensaje", "Anexo guardado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al procesar el archivo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/anulaciones")
    public ResponseEntity<List<GetMFSolicitudAnulacionProjection>> getSolicitudAnulacion() {
        try {
            List<GetMFSolicitudAnulacionProjection> solicitud = mfSolicitudAnulacionRepository.findMFSolicitudAnulacion();
            return ResponseEntity.ok(solicitud);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
