package com.mf.mf.controller.MFArchivoExcel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.dto.excel.MFAnexosDTO;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosEntregasProjection;
import com.mf.mf.repository.MFExcelRepository.MFAnexosRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.services.MFArchivoExcelServices.MFAnexosServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anexos")
@Tag(name = "Cargue de archivos anexos", description = "Cargue de campos de anexos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MFAnexosController {

    private final MFAnexosRepository mfAnexosRepository;
    private final MFHeredadosRepository mfHeredadosRepository;
    private final ObjectMapper objectMapper;

    //Obtener anexos by idHeredado
    @GetMapping("/byIDHeredado")
    public ResponseEntity<List<GetMFAnexosProjection>> findByNit(@RequestParam String idHeredado) {
        System.out.println(idHeredado);
        try {
            List<GetMFAnexosProjection> anexos = mfAnexosRepository.findAnexosByHeredado(Integer.valueOf(idHeredado));
            return ResponseEntity.ok(anexos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/guardar")
    @Transactional
    public ResponseEntity<?> guardarAnexo(@RequestBody MFAnexosDTO requestBody) {
        Map<String, String> response = new HashMap<>();
        try {

            MFAnexos anexo = new MFAnexos();

            var heredadoOpt = mfHeredadosRepository.findByIdHeredado(requestBody.getIdHeredado());

            if (heredadoOpt.isEmpty()) {
                response.put("error", "No se encontró un heredado con el id proporcionado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            MFHashHeredado heredado = heredadoOpt.get();

            anexo.setIdHeredado(requestBody.getIdHeredado());
            anexo.setCaratula(requestBody.getCaratula());
            anexo.setEstadoSituacionFinanciera(requestBody.getEstadoSituacionFinanciera());
            anexo.setEstadoResultados(requestBody.getEstadoResultados());
            anexo.setEstadoResultadosIntegral(requestBody.getEstadoResultadosIntegral());
            anexo.setFlujoEfectivoIndirecto(requestBody.getFlujoEfectivoIndirecto());
            anexo.setFlujoEfectivoDirecto(requestBody.getFlujoEfectivoDirecto());
            anexo.setEstadoCambiosPatrimonio(requestBody.getEstadoCambiosPatrimonio());
            anexo.setDictamenFiscal(requestBody.getDictamenFiscal());
            anexo.setEstado(true);

            int estado = (heredado.getEstadoEntrega() == 286) ? 460 : 284;
            // Guardar el formulario en la base de datos
            mfHeredadosRepository.actualizarEstadoEntrega(requestBody.getIdHeredado(), estado);
            mfAnexosRepository.save(anexo);

            response.put("mensaje", "Anexo guardado con éxito");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al procesar el archivo: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
