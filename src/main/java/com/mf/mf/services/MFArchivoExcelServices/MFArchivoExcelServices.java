package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.model.excel.MFDocumentosVigilado;
import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.repository.MFExcelRepository.MFDocumentosVigiladoRepository;
import com.mf.mf.repository.MFExcelRepository.MFIdentificacionVigiladoRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.*;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFArchivoExcelServices {

    @Autowired
    private MFDocumentosVigiladoRepository documentosVigiladoRepository;

    // Convertir JSON a ValidationRanges
    public ValidationRanges convertJsonToValidationRanges(String validationRangesJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(validationRangesJson, ValidationRanges.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir el JSON de rangos de validación: " + e.getMessage(), e);
        }
    }

    // Procesar archivo Excel
    public String processExcelFile(MultipartFile file, ValidationRanges validationRanges) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // Validar cada hoja en el archivo Excel
            for (ValidationRange range : validationRanges.getValidationRanges()) {
                Sheet sheet = workbook.getSheet(range.getSheetName());
                if (sheet == null) {
                    return "El archivo no es válido. La hoja '" + range.getSheetName() + "' no está presente.";
                }

                if (!validateKeywordOccurrences(sheet, range.getKeywords(), formulaEvaluator)) {
                    return "El archivo no es válido. Las palabras clave no cumplen con las cantidades esperadas en la hoja '" + range.getSheetName() + "'.";
                }
            }

            return "Archivo procesado y validado correctamente";
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage(), e);
        }
    }

    //convertir excel a csv y guardar
    @Transactional
    public String processAndSaveExcelData(MultipartFile file, String tipo, String nit) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            // Convertir Excel a CSV
            String csvContent = convertExcelToCsv(workbook);
            System.out.println(csvContent);

            // Codificar el contenido CSV a Base64
//            byte[] base64Content = encodeToBase64(csvContent);

            // Guardar en la base de datos
//            saveToDatabase(tipo, nit, base64Content);


            return "Todos los datos se han procesado y guardado correctamente.";
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage(), e);
        }
    }

    // Método para validar las ocurrencias de palabras clave en una hoja
    private boolean validateKeywordOccurrences(Sheet sheet, Map<String, Integer> keywordCounts, FormulaEvaluator formulaEvaluator) {
        Map<String, Integer> actualCounts = new HashMap<>();
        keywordCounts.forEach((keyword, count) -> actualCounts.put(keyword, 0));

        // Iterar sobre las celdas de la hoja
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = extractCellValue(cell, formulaEvaluator);

                if (cellValue != null) {
                    String finalCellValue = cellValue.trim();
                    keywordCounts.keySet().forEach(keyword -> {
                        if (finalCellValue.equalsIgnoreCase(keyword)) {
                            actualCounts.put(keyword, actualCounts.get(keyword) + 1);
                        }
                    });
                }
            }
        }

        // Verificar si las ocurrencias reales coinciden con las esperadas
        for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
            String keyword = entry.getKey();
            int expectedCount = entry.getValue();
            int actualCount = actualCounts.getOrDefault(keyword, 0);

            if (actualCount < expectedCount) {
                System.out.println("Palabra clave '" + keyword + "' encontrada " + actualCount + " veces, se esperaban " + expectedCount);
                return false;
            }
        }

        System.out.println("Todas las palabras clave cumplen con las cantidades esperadas.");
        return true;
    }

    // Método para extraer el valor de una celda
    private String extractCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        try {
            if (cell.getCellType() == CellType.FORMULA) {
                CellValue evaluatedValue = formulaEvaluator.evaluate(cell);
                if (evaluatedValue != null) {
                    switch (evaluatedValue.getCellType()) {
                        case STRING:
                            return evaluatedValue.getStringValue();
                        case NUMERIC:
                            return String.valueOf(evaluatedValue.getNumberValue());
                        default:
                            return null;
                    }
                }
            } else if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            }
        } catch (Exception e) {
            System.err.println("Error al extraer el valor de la celda: " + e.getMessage());
        }
        return null;
    }

    // Clase para representar los rangos de validación
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ValidationRanges {
        private List<ValidationRange> validationRanges;

        public List<ValidationRange> getValidationRanges() {
            return validationRanges;
        }

        public void setValidationRanges(List<ValidationRange> validationRanges) {
            this.validationRanges = validationRanges;
        }
    }

    // Clase para representar cada rango de validación
    public static class ValidationRange {
        private String sheetName;
        private Map<String, Integer> keywords = new HashMap<>();

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        public Map<String, Integer> getKeywords() {
            return keywords;
        }

        public void setKeywords(Map<String, Integer> keywords) {
            this.keywords = keywords;
        }
    }



    // Métodos auxiliares para obtener valores de celdas
    private String getStringCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());

        if (row != null) {
            Cell cell = row.getCell(ref.getCol());
            if (cell.getCellType() == CellType.FORMULA) {
                // Evaluar la fórmula
                CellValue evaluatedValue = formulaEvaluator.evaluate(cell);
                if (evaluatedValue != null && evaluatedValue.getCellType() == CellType.STRING) {
                    // Validar si es un entero o un decimal

                        return evaluatedValue.getStringValue(); // Devolver como Long si es entero

                }
            }
            else if (cell != null && cell.getCellType() == CellType.STRING) {
                    return cell.getStringCellValue();
                }
        }
        return null;
    }

    //METODO PARA CONVERTIR EXCEL A CSV
    private String convertExcelToCsv(Workbook workbook) {
        StringWriter writer = new StringWriter();
        DataFormatter formatter = new DataFormatter();

        for (Sheet sheet : workbook) {
            writer.write("### Hoja: " + sheet.getSheetName() + " ###\n"); // Separador de hojas
            for (Row row : sheet) {
                StringBuilder rowBuilder = new StringBuilder();

                for (Cell cell : row) {
                    // Formatear el valor de la celda como texto
                    String cellValue = formatter.formatCellValue(cell);
                    rowBuilder.append(cellValue).append(","); // Separador CSV
                }

                // Quitar la última coma y añadir salto de línea
                if (rowBuilder.length() > 0) {
                    rowBuilder.setLength(rowBuilder.length() - 1);
                }
                rowBuilder.append("\n");

                // Escribir la fila en el CSV
                writer.write(rowBuilder.toString());
            }
            writer.write("\n"); // Añadir salto de línea entre hojas
        }

        return writer.toString();
    }

    // Codificar texto en Base64
    private byte[] encodeToBase64(String content) {
        return Base64.getEncoder().encode(content.getBytes());
    }

    // Decodificar texto desde Base64 como String
    private String decodeFromBase64(byte[] base64Content) {
        return new String(Base64.getDecoder().decode(base64Content));
    }

    // Guardar el archivo codificado en la base de datos
    private void saveToDatabase(String tipo, String nit, byte[] base64Content) {
        MFDocumentosVigilado documento = new MFDocumentosVigilado();
        documento.setTipo(tipo);
        documento.setNit(nit);
        documento.setDocumento(base64Content);

        documentosVigiladoRepository.save(documento);
    }

    // Recuperar el archivo desde la base de datos
    public String retrieveAndDecodeFile(Long id) {
        MFDocumentosVigilado documento = documentosVigiladoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un documento con el ID proporcionado."));
        return decodeFromBase64(documento.getDocumento());
    }


    private Number getNumberCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());

            if (cell != null) {
                if (cell.getCellType() == CellType.FORMULA) {
                    // Evaluar la fórmula
                    CellValue evaluatedValue = formulaEvaluator.evaluate(cell);
                    if (evaluatedValue != null && evaluatedValue.getCellType() == CellType.NUMERIC) {
                        // Validar si es un entero o un decimal
                        System.out.println(evaluatedValue.getNumberValue());
                        return evaluatedValue.getNumberValue();

                    }
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    return cell.getNumericCellValue();



                }
            }
        }
        return null;
    }

    private Boolean getBooleanCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
        String value = getStringCellValue(sheet, cellRef, formulaEvaluator);
        return value != null && value.equalsIgnoreCase("true");
    }



    private LocalDate getDateCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());
            if (cell.getCellType() == CellType.FORMULA) {
                // Evaluar la fórmula
                CellValue evaluatedValue = formulaEvaluator.evaluate(cell);
                if (evaluatedValue != null && evaluatedValue.getCellType() == CellType.NUMERIC) {
                    // Convertir el número en una fecha
                    return DateUtil.getLocalDateTime(evaluatedValue.getNumberValue()).toLocalDate();
                }
            } else if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                return cell.getLocalDateTimeCellValue().toLocalDate();
            }
        }
        return null;
    }


}
