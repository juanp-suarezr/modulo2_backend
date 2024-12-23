package com.mf.mf.controller.MFArchivoExcel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.services.MFArchivoExcelServices.MFArchivoExcelServices;
import com.mf.mf.services.MFArchivoExcelServices.MFArchivoExcelServices.ValidationRanges;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@Tag(name = "Cargue de archivos", description = "Cargue de campos del archivo excel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MFArchivoExcelController {

    private final MFArchivoExcelServices excelService;


    @Autowired
    public MFArchivoExcelController(MFArchivoExcelServices excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("validationRanges") String validationRangesJson) {
        Map<String, String> response = new HashMap<>();

        try {
            // Convertir el JSON de rangos de validación
            ValidationRanges validationRanges = excelService.convertJsonToValidationRanges(validationRangesJson);

            // Procesar el archivo Excel
            String result = excelService.processExcelFile(file, validationRanges);

            // Retornar respuesta exitosa con el mensaje
            response.put("message", result);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Manejo específico de errores en la conversión del JSON
            response.put("error", "Error al interpretar los rangos de validación: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (RuntimeException e) {
            // Errores durante el procesamiento del archivo Excel
            response.put("error", "Error al procesar el archivo: " + e.getMessage());
            return ResponseEntity.status(500).body(response);

        } catch (Exception e) {
            // Cualquier otro error inesperado
            response.put("error", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    @PostMapping("/saveExcel")
    public ResponseEntity<Map<String, String>> processExcelFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nit") String nit,
            @RequestParam("idHeredado") String idHeredado,
            @RequestParam("fieldMapping") String fieldMappingJSON) {
        Map<String, String> response = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, String>> fieldMappings = objectMapper.readValue(
                    fieldMappingJSON, new TypeReference<Map<String, Map<String, String>>>() {});
            String result = excelService.processAndSaveExcelData(file, nit, Integer.valueOf(idHeredado), fieldMappings);
            // Preparar respuesta de éxito
            response.put("message", result);
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            // Manejar errores en la conversión de JSON
            response.put("error", "Formato de JSON inválido: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            response.put("error", "Error al procesar el archivo: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
