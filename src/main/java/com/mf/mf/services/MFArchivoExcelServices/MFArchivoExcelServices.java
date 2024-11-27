package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFArchivoExcelServices {

    // Convertir JSON a ValidationRanges
    public ValidationRanges convertJsonToValidationRanges(String validationRangesJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ValidationRanges validationRanges = objectMapper.readValue(validationRangesJson, ValidationRanges.class);
        System.out.println("Deserialized validationRanges: " + validationRanges.getValidationRanges());
        return validationRanges;
    }

    // Procesar archivo Excel
    public String processExcelFile(MultipartFile file, ValidationRanges validationRanges) throws Exception {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // Verificar si el nombre de la hoja coincide
            for (ValidationRange validationRange : validationRanges.getValidationRanges()) {
                if (sheet.getSheetName().equals(validationRange.getSheetName())) {
                    System.out.println("La hoja " + validationRange.getSheetName() + " se ha encontrado.");
                    // Validar los rangos de celdas definidos para esta hoja
                    for (Map.Entry<String, List<String>> entry : validationRange.getRanges().entrySet()) {
                        String hoja = entry.getKey();
                        List<String> ranges = entry.getValue();
                        for (String range : ranges) {
                            // Aquí puedes procesar el rango
                            System.out.println("Validando el rango: " + range + " en la hoja: " + hoja);
                        }
                    }
                }
            }

            return "Archivo procesado y validado correctamente";
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage(), e);
        }
    }

    // Clase para representar los rangos de validación
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ValidationRanges {
        private List<ValidationRange> validationRanges; // Lista de objetos ValidationRange

        // Getter y setter
        public List<ValidationRange> getValidationRanges() {
            return validationRanges;
        }

        public void setValidationRanges(List<ValidationRange> validationRanges) {
            this.validationRanges = validationRanges;
        }
    }

    // Clase para representar cada rango de validación
    public static class ValidationRange {
        private String sheetName;  // Nombre de la hoja
        private Map<String, List<String>> ranges = new HashMap<>(); // Mapa de rangos

        // Getter y setter para sheetName
        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        // Getter y setter para ranges
        public Map<String, List<String>> getRanges() {
            return ranges;
        }

        public void setRanges(Map<String, List<String>> ranges) {
            this.ranges = ranges;
        }

        // Método para capturar cualquier propiedad adicional que no se mapee explícitamente
        @JsonAnySetter
        public void addRange(String key, List<String> value) {
            this.ranges.put(key, value);
        }

    }

}
