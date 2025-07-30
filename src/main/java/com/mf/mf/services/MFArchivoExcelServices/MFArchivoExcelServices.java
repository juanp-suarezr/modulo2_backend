package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.model.excel.*;
import com.mf.mf.repository.MFExcelRepository.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFArchivoExcelServices {

    @Autowired
    private MFIdentificacionVigiladoRepository identificacionVigiladoRepository;
    @Autowired
    private MFEstadoSituacionFinancieraRepository estadoSituacionFinancieraRepository;
    @Autowired
    private MFEstadoResultadoIntegralORIRepository estadoResultadoIntegralORIRepository;
    @Autowired
    private MFEstadoResultadoRepository estadoResultadoRepository;
    @Autowired
    private MFEstadoFlujoEfectivoIndirectoRepository estadoFlujoEfectivoIndirectoRepository;
    @Autowired
    private MFEstadoFlujoEfectivoDirectoRepository estadoFlujoEfectivoDirectoRepository;
    @Autowired
    private MFDictamenRevisorFiscalRepository estadoDictamenRevisorFiscalRepository;
    @Autowired
    private MFEstadoCambioPatrimonioRepository estadoCambioPatrimonioRepository;


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

    // Método para verificar si un registro ya existe
    public boolean existsIdentificacionVigilado(Integer nit, Integer idHeredado) {
        System.out.println(identificacionVigiladoRepository.findMFIdentificacionVigiladosByNit(nit, idHeredado));
        return !identificacionVigiladoRepository.findMFIdentificacionVigiladosByNit(nit, idHeredado).isEmpty();
    }

    // Método para crear un nuevo registro
    @Transactional
    public String createIdentificacionVigilado(MultipartFile file, String nit, Integer idHeredado, Map<String, Map<String, String>> fieldMappings) {
        return processAndSaveExcelData(file, nit, idHeredado, fieldMappings);
    }

    // Método para actualizar un registro existente
    @Transactional
    public String updateIdentificacionVigilado(MultipartFile file, String nit, Integer idHeredado, Map<String, Map<String, String>> fieldMappings) {
        try {
            // Si todo se procesa correctamente, eliminar registros existentes
            identificacionVigiladoRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoSituacionFinancieraRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoResultadoRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoResultadoIntegralORIRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoFlujoEfectivoIndirectoRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoFlujoEfectivoDirectoRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoDictamenRevisorFiscalRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            estadoCambioPatrimonioRepository.deleteByNitAndIdHeredado(Integer.valueOf(nit), idHeredado);
            // Procesar y guardar los nuevos datos
            processAndSaveExcelData(file, nit, idHeredado, fieldMappings);

            return "Todos los datos se han procesado y actualizado correctamente.";
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar los datos: " + e.getMessage(), e);
        }
    }

    //Metodo para poner estado en false y guardar historial
    public void disablePreviousExcelRecords(Integer idHeredado) {
        identificacionVigiladoRepository.updateEstadoByIdHeredado(idHeredado);
        estadoSituacionFinancieraRepository.updateEstadoByIdHeredado(idHeredado);
        estadoResultadoRepository.updateEstadoByIdHeredado(idHeredado);
        estadoResultadoIntegralORIRepository.updateEstadoByIdHeredado(idHeredado);
        estadoFlujoEfectivoIndirectoRepository.updateEstadoByIdHeredado(idHeredado);
        estadoFlujoEfectivoDirectoRepository.updateEstadoByIdHeredado(idHeredado);
        estadoDictamenRevisorFiscalRepository.updateEstadoByIdHeredado(idHeredado);
        estadoCambioPatrimonioRepository.updateEstadoByIdHeredado(idHeredado);

    }


    //guardar los campos excel
    @Transactional
    public String processAndSaveExcelData(MultipartFile file, String nit, Integer idHeredado, Map<String, Map<String, String>> fieldMappings) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // Iterar por las hojas y sus mapeos
            fieldMappings.forEach((sheetName, mappings) -> {
                Class<?> entityClass = getEntityClass(sheetName);
                processSheetData(workbook, sheetName, mappings, formulaEvaluator, nit, entityClass, idHeredado);
            });

            return "Todos los datos se han procesado y guardado correctamente.";
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage(), e);
        }
    }


    // Método auxiliar para obtener la clase de entidad correspondiente
    private Class<?> getEntityClass(String sheetName) {
        switch (sheetName) {
            case "Identificación del Vigilado":
                return MFIdentificacionVigilado.class;
            case "ESF":
                return MFEstadoSituacionFinanciera.class;
            case "ER":
                return MFEstadoResultados.class;
            case "ORI":
                return MFEstadoResultadoIntegralORI.class;
            case "EFE-indirecto":
                return MFEstadoFlujoEfectivoIndirecto.class;
            case "EFE-directo":
                return MFEstadoFlujoEfectivoDirecto.class;
            case "Dictamen":
                return MFDictamenRevisorFiscal.class;
            case "ECP":
                return MFEstadoCambioPatrimonio.class;
            default:
                throw new IllegalArgumentException("La hoja '" + sheetName + "' no está mapeada para su procesamiento.");
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


    private <T> void processSheetData(
            Workbook workbook,
            String sheetName,
            Map<String, String> mappings,
            FormulaEvaluator formulaEvaluator,
            String nit,
            Class<T> entityClass,
            Integer idHeredado
    ) {

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("La hoja '" + sheetName + "' no está presente en el archivo.");
        }

        try {
            // Identificar referencias múltiples
            int maxRecords = 1;
            for (String cellRefs : mappings.values()) {
                int refCount = cellRefs.split(",\\s*").length;
                maxRecords = Math.max(maxRecords, refCount); // Determinar el máximo de registros a generar
            }

            // Crear un registro por cada conjunto de referencias
            for (int recordIndex = 0; recordIndex < maxRecords; recordIndex++) {
                // Crear instancia de la entidad
                T entity = entityClass.getDeclaredConstructor().newInstance();

                for (Map.Entry<String, String> mapping : mappings.entrySet()) {
                    String field = mapping.getKey();
                    String cellRefs = mapping.getValue();
                    String[] refArray = cellRefs.split(",\\s*");

                    // Usar la referencia actual si existe, de lo contrario ignorar
                    if (recordIndex < refArray.length) {
                        String cellRef = refArray[recordIndex];
                        Object cellValue = getCellValue(nit, sheet, cellRef, formulaEvaluator, entityClass.getDeclaredField(field).getType());


                        if (field.equals("nitSinDigitoVerificacion")) {
                            if (!cellValue.toString().equals(nit)) {
                                System.out.println("cell value: " + cellValue);
                                throw new IllegalArgumentException("El nit diligenciado " +cellValue+ " no es mismo que el nit registrado" + nit);
                            }
                        }

                        // Asignar el valor al campo correspondiente
                        Field declaredField = entityClass.getDeclaredField(field);

                        declaredField.setAccessible(true);
                        declaredField.set(entity, cellValue);
                    }
                }

                // Asignar el estado y el NIT
                setEntityDefaults(entity, nit, idHeredado, recordIndex);

                // Guardar en la base de datos
                System.out.println(entity);
                saveEntity(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la hoja '" + sheetName + "': " + e.getMessage(), e);
        }
    }

    // Método para asignar valores predeterminados (estado y NIT)
    private <T> void setEntityDefaults(T entity, String nit, Integer idHeredado, Integer contador) {
        try {
            Field estadoField = entity.getClass().getDeclaredField("estado");
            estadoField.setAccessible(true);
            estadoField.set(entity, true);

            Field nitField = entity.getClass().getDeclaredField("nit");
            nitField.setAccessible(true);
            nitField.set(entity, Integer.parseInt(nit));

            Field idHeredadoField = entity.getClass().getDeclaredField("idHeredado");
            idHeredadoField.setAccessible(true);
            idHeredadoField.set(entity, idHeredado);

            Field actualField = entity.getClass().getDeclaredField("actual");
            if (contador == 0) {
                actualField.setAccessible(true);
                actualField.set(entity, true);
            } else {
                actualField.setAccessible(true);
                actualField.set(entity, false);
            }
        } catch (NoSuchFieldException e) {
            // Si no existe el campo, continuar sin hacer nada
        } catch (Exception e) {
            throw new RuntimeException("Error al asignar valores predeterminados: " + e.getMessage(), e);
        }
    }



    private Object getCellValue(String nit, Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator, Class<?> fieldType) {
        CellReference ref = new CellReference(cellRef);

        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());
            if (cell != null) {
                if (fieldType == Integer.class || fieldType == int.class) {

                    System.out.println("info nit:" + cell);

                    return getNumberCellValue(sheet, cellRef, formulaEvaluator);
                } else if (fieldType == String.class) {
                    return getStringCellValue(sheet, cellRef, formulaEvaluator);
                } else if (fieldType == LocalDate.class) {
                    return getDateCellValue(sheet, cellRef, formulaEvaluator);
                } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                    return getBooleanCellValue(sheet, cellRef, formulaEvaluator);
                } else if(fieldType == long.class || fieldType == Long.class) {
                    return getNumberLongCellValue(sheet, cellRef, formulaEvaluator);
                }
            }
        }
        return null;
    }


    @Transactional
    protected void saveEntity(Object entity) {
        if (entity instanceof MFIdentificacionVigilado) {
            identificacionVigiladoRepository.save((MFIdentificacionVigilado) entity);
        } else if (entity instanceof MFEstadoSituacionFinanciera) {
            estadoSituacionFinancieraRepository.save((MFEstadoSituacionFinanciera) entity);
        } else if (entity instanceof MFEstadoResultadoIntegralORI) {
            estadoResultadoIntegralORIRepository.save((MFEstadoResultadoIntegralORI) entity);
        } else if (entity instanceof MFEstadoResultados) {
            estadoResultadoRepository.save((MFEstadoResultados) entity);
    }else if (entity instanceof MFEstadoFlujoEfectivoIndirecto) {
            estadoFlujoEfectivoIndirectoRepository.save((MFEstadoFlujoEfectivoIndirecto) entity);
        }else if (entity instanceof MFEstadoFlujoEfectivoDirecto) {
            estadoFlujoEfectivoDirectoRepository.save((MFEstadoFlujoEfectivoDirecto) entity);
        }else if (entity instanceof MFDictamenRevisorFiscal) {
            estadoDictamenRevisorFiscalRepository.save((MFDictamenRevisorFiscal) entity);
        }else if (entity instanceof MFEstadoCambioPatrimonio) {
            estadoCambioPatrimonioRepository.save((MFEstadoCambioPatrimonio) entity);
    } else {
            throw new IllegalArgumentException("Tipo de entidad no soportado: " + entity.getClass().getName());
        }
    }


    // Métodos auxiliares para obtener valores de celdas
    private String getStringCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());

        if (row != null) {
            Cell cell = row.getCell(ref.getCol());
            System.out.println(cell.getCellType());
            if (cell.getCellType() == CellType.FORMULA) {
                // Evaluar la fórmula
                CellValue evaluatedValue = formulaEvaluator.evaluate(cell);
                if (evaluatedValue != null && evaluatedValue.getCellType() == CellType.STRING) {
                    // Validar si es un entero o un decimal
                    System.out.println(evaluatedValue.getStringValue());
                        return evaluatedValue.getStringValue();

                }
            }
            else if (cell != null && cell.getCellType() == CellType.STRING) {
                    return cell.getStringCellValue();
            } else if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            }
        }
        return null;
    }


    private Integer getNumberCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
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
                        return (int) evaluatedValue.getNumberValue();

                    }
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    return (int) cell.getNumericCellValue();



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

    private Long getNumberLongCellValue(Sheet sheet, String cellRef, FormulaEvaluator formulaEvaluator) {
        CellReference ref = new CellReference(cellRef);
        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());

            if (cell != null) {
                if (cell.getCellType() == CellType.FORMULA) {
                    CellValue evaluatedValue = formulaEvaluator.evaluate(cell);
                    if (evaluatedValue != null && evaluatedValue.getCellType() == CellType.NUMERIC) {
                        double number = evaluatedValue.getNumberValue();
                        System.out.println(number);
                        return (long) number; // Usa long para soportar valores grandes
                    }
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    return (long) cell.getNumericCellValue();
                }
            }
        }
        return null;
    }


}