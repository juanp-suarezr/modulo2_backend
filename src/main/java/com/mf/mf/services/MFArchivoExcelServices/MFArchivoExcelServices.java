package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.repository.MFExcelRepository.MFIdentificacionVigiladoRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFArchivoExcelServices {

    @Autowired
    private MFIdentificacionVigiladoRepository identificacionVigiladoRepository;

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

    //guardar los campos excel
    @Transactional
    public String processAndSaveExcelData(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            // Procesar y guardar datos de Identificación del Vigilado
            processAndSaveIdentificacionVigilado(workbook);

            // Procesar y guardar datos de otra hoja (ejemplo)
//            processAndSaveOtraEntidad(workbook);

            // Más procesamientos según sea necesario

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

    private void processAndSaveIdentificacionVigilado(Workbook workbook) {
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("Identificación del Vigilado");
        if (sheet == null) {
            throw new IllegalArgumentException("La hoja 'Identificación del Vigilado' no está presente en el archivo.");
        }
        CellReference ref = new CellReference("F30");
        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());

            System.out.println(cell);
        }


        MFIdentificacionVigilado vigilado = new MFIdentificacionVigilado();
        vigilado.setEstado(true);
        vigilado.setNitSinDigitoVerificacion(getNumberCellValue(sheet, "F9"));
        vigilado.setDigitoVerificacion(getNumberCellValue(sheet, "F10"));
        vigilado.setNombreSociedad(getStringCellValue(sheet, "F11"));
        vigilado.setGrupoNiifReporte(getStringCellValue(sheet, "F12"));
        vigilado.setTipoEstadosFinancieros(getStringCellValue(sheet, "F13"));
        vigilado.setTipoVinculacionEconomica(getStringCellValue(sheet, "F14"));
        vigilado.setTipoSubordinada(getStringCellValue(sheet, "F15"));
        vigilado.setVinculadosEconomicos(getNumberCellValue(sheet, "F16").toString());
        vigilado.setNombreVinculadoEconomico1(getStringCellValue(sheet, "F17"));
        vigilado.setNitVinculadoEconomico1(getStringCellValue(sheet, "F18"));
        vigilado.setNombreVinculadoEconomico2(getStringCellValue(sheet, "F19"));
        vigilado.setNitVinculadoEconomico2(getStringCellValue(sheet, "F20"));
        vigilado.setNombreVinculadoEconomico3(getStringCellValue(sheet, "F21"));
        vigilado.setNitVinculadoEconomico3(getStringCellValue(sheet, "F22"));
        vigilado.setNombreVinculadoEconomico4(getStringCellValue(sheet, "F23"));
        vigilado.setNitVinculadoEconomico4(getStringCellValue(sheet, "F24"));
        vigilado.setNombreVinculadoEconomico5(getStringCellValue(sheet, "F25"));
        vigilado.setNitVinculadoEconomico5(getStringCellValue(sheet, "F26"));
        vigilado.setFechaInicialEstadosFinancieros(getDateCellValue(sheet, "F27", formulaEvaluator));
        vigilado.setFechaCorteEstadosFinancieros(getDateCellValue(sheet, "F28", formulaEvaluator));
        vigilado.setMonedaPresentacion(getStringCellValue(sheet, "F29"));
        vigilado.setFechaReporte(getDateCellValue(sheet, "F30", formulaEvaluator));
        vigilado.setPeriodicidadPresentacion(getStringCellValue(sheet, "F31"));
        vigilado.setAnoActualReporte((Integer) getNumberCellValue(sheet, "F32"));
        vigilado.setAnoComparativo((Integer) getNumberCellValue(sheet, "F33"));

        System.out.println(vigilado);
        // Guardar en la base de datos
        identificacionVigiladoRepository.save(vigilado);
    }



    // Métodos auxiliares para obtener valores de celdas
    private String getStringCellValue(Sheet sheet, String cellRef) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());

        if (row != null) {
            Cell cell = row.getCell(ref.getCol());
            if (cell != null && cell.getCellType() == CellType.STRING) {
                    return cell.getStringCellValue();
                }
        }
        return null;
    }


    private Number getNumberCellValue(Sheet sheet, String cellRef) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                System.out.println(cell.getNumericCellValue());
                System.out.println(cell.getCellType());
                return cell.getNumericCellValue();
            }
        }
        return null;
    }

    private Boolean getBooleanCellValue(Sheet sheet, String cellRef) {
        String value = getStringCellValue(sheet, cellRef);
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
