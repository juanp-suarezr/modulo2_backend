package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.util.CellReference;
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
            List<String> validSheetNames = validationRanges.getValidationRanges().stream()
                    .map(ValidationRange::getSheetName)
                    .toList();

            // Verificar todas las hojas
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();

                // Validar si el nombre de la hoja está en la lista de nombres válidos
                if (!validSheetNames.contains(sheetName)) {
                    return "El archivo no es válido. La hoja '" + sheetName + "' no está permitida.";
                }

                // Si la hoja es válida, realizar las validaciones de rangos
                for (ValidationRange validationRange : validationRanges.getValidationRanges()) {
                    if (sheetName.equals(validationRange.getSheetName())) {
                        System.out.println("La hoja " + validationRange.getSheetName() + " se ha encontrado.");

                        // Validar los rangos de celdas definidos para esta hoja
                        for (Map.Entry<String, List<String>> entry : validationRange.getRanges().entrySet()) {
                            List<String> ranges = entry.getValue();
                            for (String range : ranges) {
                                if (!validateRange(sheet, range)) {
                                    return "El archivo no es válido. El rango '" + range + "' en la hoja '" + sheetName + "' no es válido.";
                                }
                            }
                        }
                    }
                }
            }

            return "Archivo procesado y validado correctamente";
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage(), e);
        }
    }

    // Método para validar un rango dentro de una hoja
    private boolean validateRange(Sheet sheet, String range) {
        try {
            String[] bounds = range.split(":"); // Dividir el rango en límites ("F9" y "F29")
            CellReference start = new CellReference(bounds[0]); // Parsear el inicio del rango
            CellReference end = new CellReference(bounds[1]);   // Parsear el final del rango

            // Recorrer las celdas dentro del rango
            for (int row = start.getRow(); row <= end.getRow(); row++) {
                for (int col = start.getCol(); col <= end.getCol(); col++) {
                    Row currentRow = sheet.getRow(row);
                    if (currentRow == null || currentRow.getCell(col) == null) {
                        System.out.println("Celda vacía en " + row + ", " + col);
                        return false; // Celda no válida o fuera de rango
                    }
                    // Puedes añadir reglas adicionales de validación aquí, si es necesario
                }
            }

            return true; // Todas las celdas en el rango son válidas
        } catch (Exception e) {
            System.err.println("Error al validar el rango: " + range + ". " + e.getMessage());
            return false; // Error al procesar el rango
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

        // Metodo para capturar cualquier propiedad adicional que no se mapee explícitamente
        @JsonAnySetter
        public void addRange(String key, List<String> value) {
            this.ranges.put(key, value);
        }

    }

}
