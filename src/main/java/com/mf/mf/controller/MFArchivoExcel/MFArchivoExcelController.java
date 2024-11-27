package com.mf.mf.controller.MFArchivoExcel;

import com.mf.mf.services.MFArchivoExcelServices.MFArchivoExcelServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("validationRanges") String validationRangesJson) {
        try {
            // Convertir el JSON de validación a un objeto ValidationRanges
            MFArchivoExcelServices.ValidationRanges validationRanges = excelService.convertJsonToValidationRanges(validationRangesJson);

            // Procesar el archivo Excel y aplicar validaciones
            String result = excelService.processExcelFile(file, validationRanges);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }

}

