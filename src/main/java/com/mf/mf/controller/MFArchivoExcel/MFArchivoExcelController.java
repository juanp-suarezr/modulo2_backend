package com.mf.mf.controller.MFArchivoExcel;

import com.mf.mf.services.MFArchivoExcelServices.MFArchivoExcelServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@Tag(name = "Cargue de archivos", description = "Cargue de campos del archivo excel")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class MFArchivoExcelController {

    @Autowired
    private final MFArchivoExcelServices excelService;

    public MFArchivoExcelController(MFArchivoExcelServices excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("validationRanges") String validationRangesJson) {
        try {
            MFArchivoExcelServices.ValidationRanges validationRanges = excelService.convertJsonToValidationRanges(validationRangesJson);
            String result = excelService.processExcelFile(file, validationRanges);

            // Retorna un JSON con el resultado
            Map<String, String> response = new HashMap<>();
            response.put("message", result);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al procesar el archivo: " + e.getMessage());

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}

