package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.model.excel.MFEstadoResultadoIntegralORI;
import com.mf.mf.model.excel.MFEstadoSituacionFinanciera;
import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.repository.MFExcelRepository.MFEstadoResultadoIntegralORIRepository;
import com.mf.mf.repository.MFExcelRepository.MFEstadoSituacionFinancieraRepository;
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
    public String processAndSaveExcelData(MultipartFile file, String nit) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            // Procesar y guardar datos de Identificación del Vigilado
            processAndSaveIdentificacionVigilado(workbook, nit);

//             Procesar y guardar datos de ESF
            processAndSaveEstadoSituacionFinanciera(workbook, nit);

            // Procesar y guardar datos de ORI
            processAndSaveEstadoResultadosORI(workbook, nit);

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

    //METODO INDENTIFICACION VIGILADO SHEET
    private void processAndSaveIdentificacionVigilado(Workbook workbook, String nit) {
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("Identificación del Vigilado");
        if (sheet == null) {
            throw new IllegalArgumentException("La hoja 'Identificación del Vigilado' no está presente en el archivo.");
        }
        CellReference ref = new CellReference("F32");
        Row row = sheet.getRow(ref.getRow());
        if (row != null) {
            Cell cell = row.getCell(ref.getCol());

            System.out.println(cell);
        }


        MFIdentificacionVigilado vigilado = new MFIdentificacionVigilado();
        vigilado.setEstado(true);
        vigilado.setNitSinDigitoVerificacion(getNumberCellValue(sheet, "F9", formulaEvaluator));
        vigilado.setDigitoVerificacion(getNumberCellValue(sheet, "F10", formulaEvaluator));
        vigilado.setNombreSociedad(getStringCellValue(sheet, "F11", formulaEvaluator));
        vigilado.setGrupoNiifReporte(getStringCellValue(sheet, "F12", formulaEvaluator));
        vigilado.setTipoEstadosFinancieros(getStringCellValue(sheet, "F13", formulaEvaluator));
        vigilado.setTipoVinculacionEconomica(getStringCellValue(sheet, "F14", formulaEvaluator));
        vigilado.setTipoSubordinada(getStringCellValue(sheet, "F15", formulaEvaluator));
        vigilado.setVinculadosEconomicos(getNumberCellValue(sheet, "F16", formulaEvaluator).toString());
        vigilado.setNombreVinculadoEconomico1(getStringCellValue(sheet, "F17", formulaEvaluator));
        vigilado.setNitVinculadoEconomico1(getStringCellValue(sheet, "F18", formulaEvaluator));
        vigilado.setNombreVinculadoEconomico2(getStringCellValue(sheet, "F19", formulaEvaluator));
        vigilado.setNitVinculadoEconomico2(getStringCellValue(sheet, "F20", formulaEvaluator));
        vigilado.setNombreVinculadoEconomico3(getStringCellValue(sheet, "F21", formulaEvaluator));
        vigilado.setNitVinculadoEconomico3(getStringCellValue(sheet, "F22", formulaEvaluator));
        vigilado.setNombreVinculadoEconomico4(getStringCellValue(sheet, "F23", formulaEvaluator));
        vigilado.setNitVinculadoEconomico4(getStringCellValue(sheet, "F24", formulaEvaluator));
        vigilado.setNombreVinculadoEconomico5(getStringCellValue(sheet, "F25", formulaEvaluator));
        vigilado.setNitVinculadoEconomico5(getStringCellValue(sheet, "F26", formulaEvaluator));
        vigilado.setFechaInicialEstadosFinancieros(getDateCellValue(sheet, "F27", formulaEvaluator));
        vigilado.setFechaCorteEstadosFinancieros(getDateCellValue(sheet, "F28", formulaEvaluator));
        vigilado.setMonedaPresentacion(getStringCellValue(sheet, "F29", formulaEvaluator));
        vigilado.setFechaReporte(getDateCellValue(sheet, "F30", formulaEvaluator));
        vigilado.setPeriodicidadPresentacion(getStringCellValue(sheet, "F31", formulaEvaluator));
        vigilado.setAnoActualReporte(getNumberCellValue(sheet, "F32", formulaEvaluator) != null ? getNumberCellValue(sheet, "F32", formulaEvaluator) : 0);
        vigilado.setAnoComparativo(getNumberCellValue(sheet, "F33", formulaEvaluator) != null ? getNumberCellValue(sheet, "F33", formulaEvaluator) : 0);


        // Guardar en la base de datos
        identificacionVigiladoRepository.save(vigilado);
    }
    //METODO ESTADO DE SITUACION FINANCIERA SHEET
    private void processAndSaveEstadoSituacionFinanciera(Workbook workbook, String nit) {
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("ESF");
        if (sheet == null) {
            throw new IllegalArgumentException("La hoja 'Estado Situación Financiera' no está presente en el archivo.");
        }

        MFEstadoSituacionFinanciera estadoFinanciero = new MFEstadoSituacionFinanciera();
        estadoFinanciero.setEstado(true);
        estadoFinanciero.setTotalActivosCorrientes(getNumberCellValue(sheet, "H12", formulaEvaluator));
        estadoFinanciero.setEfectivoYEquivalentesAlEfectivo(getNumberCellValue(sheet, "H13", formulaEvaluator));
        estadoFinanciero.setEfectivoRestringido(getNumberCellValue(sheet, "H14", formulaEvaluator));
        estadoFinanciero.setInversionesCortoPlazo(getNumberCellValue(sheet, "H15", formulaEvaluator));
        estadoFinanciero.setCuentasComercialesCobrarOperacionalesClientes(getNumberCellValue(sheet, "H16", formulaEvaluator));
        estadoFinanciero.setCuentasPorCobrarConPartesRelacionadas1(getNumberCellValue(sheet, "H17", formulaEvaluator));
        estadoFinanciero.setActivosBiologicos(getNumberCellValue(sheet, "H18", formulaEvaluator));
        estadoFinanciero.setOtrasCuentasPorCobrar1(getNumberCellValue(sheet, "H19", formulaEvaluator));
        estadoFinanciero.setPagosAnticipados(getNumberCellValue(sheet, "H20", formulaEvaluator));
        estadoFinanciero.setInventariosCorrientes(getNumberCellValue(sheet, "H21", formulaEvaluator));
        estadoFinanciero.setActivosPorImpuestos(getNumberCellValue(sheet, "H22", formulaEvaluator));
        estadoFinanciero.setActivosDistintosAlEfectivoPignoradosComoGarantia(getNumberCellValue(sheet, "H23", formulaEvaluator));
        estadoFinanciero.setOtrosActivosFinancieros1(getNumberCellValue(sheet, "H24", formulaEvaluator));
        estadoFinanciero.setOtrosActivosNoFinancieros1(getNumberCellValue(sheet, "H25", formulaEvaluator));
        estadoFinanciero.setValidacionActivosCorrientes(getStringCellValue(sheet, "H26", formulaEvaluator));

        estadoFinanciero.setTotalActivosNoCorrientes(getNumberCellValue(sheet, "H28", formulaEvaluator));
        estadoFinanciero.setDepositosYOtrosActivos1(getNumberCellValue(sheet, "H29", formulaEvaluator));
        estadoFinanciero.setInversionesLargoPlazo(getNumberCellValue(sheet, "H30", formulaEvaluator));
        estadoFinanciero.setCuentasComercialesPorCobrar(getNumberCellValue(sheet, "H31", formulaEvaluator));
        estadoFinanciero.setCuentasPorCobrarConPartesRelacionadas2(getNumberCellValue(sheet, "H32", formulaEvaluator));
        estadoFinanciero.setOtrasCuentasPorCobrar2(getNumberCellValue(sheet, "H33", formulaEvaluator));
        estadoFinanciero.setPropiedadDeInversion(getNumberCellValue(sheet, "H34", formulaEvaluator));
        estadoFinanciero.setActivosIntangiblesYCreditoMercantil(getNumberCellValue(sheet, "H35", formulaEvaluator));
        estadoFinanciero.setPropiedadesPlantaYEquipo(getNumberCellValue(sheet, "H36", formulaEvaluator));
        estadoFinanciero.setActivosBiologicosNoCorrientes(getNumberCellValue(sheet, "H37", formulaEvaluator));
        estadoFinanciero.setInversionesContabilizadasParticipacion(getNumberCellValue(sheet, "H38", formulaEvaluator));
        estadoFinanciero.setInversionesSubsidiarNegocios(getNumberCellValue(sheet, "H39", formulaEvaluator));
        estadoFinanciero.setPlusvalia(getNumberCellValue(sheet, "H40", formulaEvaluator));
        estadoFinanciero.setInventariosNoCorrientes(getNumberCellValue(sheet, "H41", formulaEvaluator));
        estadoFinanciero.setActivosPorImpuestosDiferidos(getNumberCellValue(sheet, "H42", formulaEvaluator));
        estadoFinanciero.setActivosPorImpuestosCorrientesNoCorrientes(getNumberCellValue(sheet, "H43", formulaEvaluator));
        estadoFinanciero.setOtrosActivosFinancieros2(getNumberCellValue(sheet, "H44", formulaEvaluator));
        estadoFinanciero.setOtrosActivosNoFinancieros2(getNumberCellValue(sheet, "H45", formulaEvaluator));
        estadoFinanciero.setActivosDistintosAlEfectivo(getNumberCellValue(sheet, "H46", formulaEvaluator));
        estadoFinanciero.setValidacionActivosNoCorrientes(getStringCellValue(sheet, "H47", formulaEvaluator));
        estadoFinanciero.setValidacionActivos(getStringCellValue(sheet, "H49", formulaEvaluator));
        
        estadoFinanciero.setPasivos(getNumberCellValue(sheet, "H51", formulaEvaluator));
        estadoFinanciero.setPasivosCorrientes(getNumberCellValue(sheet, "H53", formulaEvaluator));
        estadoFinanciero.setCuentasPorPagarComercialesProveedores(getNumberCellValue(sheet, "H54", formulaEvaluator));
        estadoFinanciero.setOtrasCuentasPorPagar(getNumberCellValue(sheet, "H55", formulaEvaluator));
        estadoFinanciero.setPasivosPorImpuestos1(getNumberCellValue(sheet, "H56", formulaEvaluator));
        estadoFinanciero.setDeudaFinanciera(getNumberCellValue(sheet, "H57", formulaEvaluator));
        estadoFinanciero.setOtrosPasivosNoFinancieros1(getNumberCellValue(sheet, "H58", formulaEvaluator));
        estadoFinanciero.setDepositosYOtrosActivos2(getNumberCellValue(sheet, "H59", formulaEvaluator));
        estadoFinanciero.setPorcionCorrienteDeuda(getNumberCellValue(sheet, "H60", formulaEvaluator));
        estadoFinanciero.setCuentasPorPagarConPartesRelacionadas3(getNumberCellValue(sheet, "H61", formulaEvaluator));
        estadoFinanciero.setOtrasProvisiones(getNumberCellValue(sheet, "H62", formulaEvaluator));
        estadoFinanciero.setProvisionesPorBeneficiosAEmpleados1(getNumberCellValue(sheet, "H63", formulaEvaluator));
        estadoFinanciero.setIngresosRecibidosPorCuentaDeTerceros(getNumberCellValue(sheet, "H64", formulaEvaluator));
        estadoFinanciero.setValidacionPasivosCorrientes(getStringCellValue(sheet, "H65", formulaEvaluator));

        estadoFinanciero.setPasivosNoCorrientes(getNumberCellValue(sheet, "H67", formulaEvaluator));
        estadoFinanciero.setDeudaALargoPlazo(getNumberCellValue(sheet, "H68", formulaEvaluator));
        estadoFinanciero.setCuentasPorPagar(getNumberCellValue(sheet, "H69", formulaEvaluator));
        estadoFinanciero.setProvisionesPorBeneficiosAEmpleados2(getNumberCellValue(sheet, "H70", formulaEvaluator));
        estadoFinanciero.setPasivosPorImpuestosDiferidos(getNumberCellValue(sheet, "H71", formulaEvaluator));
        estadoFinanciero.setIngresosRecibidosPorAnticipado(getNumberCellValue(sheet, "H72", formulaEvaluator));
        estadoFinanciero.setOtrosPasivosNoFinancieros2(getNumberCellValue(sheet, "H73", formulaEvaluator));
        estadoFinanciero.setOtrosPasivosFinancieros(getNumberCellValue(sheet, "H74", formulaEvaluator));
        estadoFinanciero.setPasivosPorImpuestos2(getNumberCellValue(sheet, "H75", formulaEvaluator));
        estadoFinanciero.setValidacionPasivosNoCorrientes(getStringCellValue(sheet, "H76", formulaEvaluator));

        estadoFinanciero.setValidacionPasivos(getStringCellValue(sheet, "H78", formulaEvaluator));
        estadoFinanciero.setPatrimonio(getNumberCellValue(sheet, "H80", formulaEvaluator));
        estadoFinanciero.setCapitalPagado(getNumberCellValue(sheet, "H81", formulaEvaluator));
        estadoFinanciero.setPrimaDeEmision(getNumberCellValue(sheet, "H82", formulaEvaluator));
        estadoFinanciero.setReadquisicionDeInstrumentosDePatrimonioPropio(getNumberCellValue(sheet, "H83", formulaEvaluator));
        estadoFinanciero.setInversionSumplementariaAlCapitalAsignado(getNumberCellValue(sheet, "H84", formulaEvaluator));
        estadoFinanciero.setOtrasParticipacionesEnElPatrimonio(getNumberCellValue(sheet, "H85", formulaEvaluator));
        estadoFinanciero.setSuperavitPorRevaluacion(getNumberCellValue(sheet, "H86", formulaEvaluator));
        estadoFinanciero.setReservaLegal(getNumberCellValue(sheet, "H87", formulaEvaluator));
        estadoFinanciero.setOtrasReservas(getNumberCellValue(sheet, "H88", formulaEvaluator));
        estadoFinanciero.setUtilidadYorPerdidaDelEjercicio(getNumberCellValue(sheet, "H89", formulaEvaluator));
        estadoFinanciero.setUtilidadYorPerdidaAcumulada(getNumberCellValue(sheet, "H90", formulaEvaluator));
        estadoFinanciero.setGananciasAcumuladasPorEfectoDeLaConvergencia(getNumberCellValue(sheet, "H91", formulaEvaluator));
        estadoFinanciero.setGananciasAcumuladasDiferentesALasGeneradasPorEfectoDeLaConvergencia(getNumberCellValue(sheet, "H92", formulaEvaluator));
        estadoFinanciero.setValidacionPatrimonio(getStringCellValue(sheet, "H93", formulaEvaluator));
        estadoFinanciero.setValidacionGananciaPerdidaNeta(getStringCellValue(sheet, "H95", formulaEvaluator));
        estadoFinanciero.setTotalPasivoPatrimonio(getNumberCellValue(sheet, "H97", formulaEvaluator));
        estadoFinanciero.setValidacionEcuacionPatrimonial(getStringCellValue(sheet, "H99", formulaEvaluator));
        estadoFinanciero.setNit(parseInt(nit));

        System.out.println("validacionActivos: " + estadoFinanciero.getValidacionActivos());

        System.out.println(estadoFinanciero);



        // Guardar en la base de datos
        estadoSituacionFinancieraRepository.save(estadoFinanciero);
    }
    //METODO ESTADO DE RESULTADOS ORI SHEET
    private void processAndSaveEstadoResultadosORI(Workbook workbook, String nit) {
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheet("ORI");
        if (sheet == null) {
            throw new IllegalArgumentException("La hoja 'Estado Situación Financiera' no está presente en el archivo.");
        }

        MFEstadoResultadoIntegralORI estadoResORI = new MFEstadoResultadoIntegralORI();
        estadoResORI.setEstado(true);
        estadoResORI.setGananciaPerdidaNeta(getNumberCellValue(sheet, "J10", formulaEvaluator));
        estadoResORI.setOtroResultadoIntegralDiferenciasCambioConversion(getNumberCellValue(sheet, "J13", formulaEvaluator));
        estadoResORI.setOtroResultadoIntegralGananciasActuariales(getNumberCellValue(sheet, "J14", formulaEvaluator));
        estadoResORI.setOtroResultadoIntegralGananciasRevaluacion(getNumberCellValue(sheet, "J15", formulaEvaluator));
        estadoResORI.setTotalOtroResultadoIntegralNoReclasificable(getNumberCellValue(sheet, "J16", formulaEvaluator));
        estadoResORI.setGananciasCoberturasFlujosEfectivo(getNumberCellValue(sheet, "J18", formulaEvaluator));
        estadoResORI.setTotalOtroResultadoIntegralReclasificable(getNumberCellValue(sheet, "J19", formulaEvaluator));
        estadoResORI.setParticipacionOtroResultadoIntegralSubsidiarias(getNumberCellValue(sheet, "J20", formulaEvaluator));
        estadoResORI.setTotalOtroResultadoIntegral(getNumberCellValue(sheet, "J21", formulaEvaluator));
        estadoResORI.setResultadoIntegralTotal(getNumberCellValue(sheet, "J22", formulaEvaluator));
        estadoResORI.setResultadoIntegralPropietariosControladora(getNumberCellValue(sheet, "J24", formulaEvaluator));
        estadoResORI.setResultadoIntegralParticipacionesNoControladoras(getNumberCellValue(sheet, "J25", formulaEvaluator));
        estadoResORI.setValidacionEstadoResultados(getStringCellValue(sheet, "J27", formulaEvaluator));
        estadoResORI.setNit(parseInt(nit));

        System.out.println(estadoResORI);



        // Guardar en la base de datos
        estadoResultadoIntegralORIRepository.save(estadoResORI);
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

                        return evaluatedValue.getStringValue();

                }
            }
            else if (cell != null && cell.getCellType() == CellType.STRING) {
                    return cell.getStringCellValue();
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


}
