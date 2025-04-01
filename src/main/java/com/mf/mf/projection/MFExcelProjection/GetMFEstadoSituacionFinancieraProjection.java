package com.mf.mf.projection.MFExcelProjection;

import java.time.LocalDate;

public interface GetMFEstadoSituacionFinancieraProjection {

    Long getId();

    Boolean getEstado();

    Long getTotalActivosCorrientes();

    Long getEfectivoYEquivalentesAlEfectivo();

    Long getEfectivoRestringido();

    Long getInversionesCortoPlazo();

    Long getCuentasComercialesCobrarOperacionalesClientes();

    Long getCuentasPorCobrarConPartesRelacionadas1();

    Long getActivosBiologicos();

    Long getOtrasCuentasPorCobrar1();

    Long getPagosAnticipados();

    Long getInventariosCorrientes();

    Long getActivosPorImpuestos();

    Long getActivosDistintosAlEfectivoPignoradosComoGarantia();

    Long getOtrosActivosFinancieros1();

    Long getOtrosActivosNoFinancieros1();

    String getValidacionActivosCorrientes();

    Long getTotalActivosNoCorrientes();

    Long getDepositosYOtrosActivos1();

    Long getInversionesLargoPlazo();

    Long getCuentasComercialesPorCobrar();

    Long getCuentasPorCobrarConPartesRelacionadas2();

    Long getOtrasCuentasPorCobrar2();

    Long getPropiedadDeInversion();

    Long getActivosIntangiblesYCreditoMercantil();

    Long getPropiedadesPlantaYEquipo();

    Long getActivosBiologicosNoCorrientes();

    Long getInversionesContabilizadasParticipacion();

    Long getInversionesSubsidiarNegocios();

    Long getPlusvalia();

    Long getInventariosNoCorrientes();

    Long getActivosPorImpuestosDiferidos();

    Long getActivosPorImpuestosCorrientesNoCorrientes();

    Long getOtrosActivosFinancieros2();

    Long getOtrosActivosNoFinancieros2();

    Long getActivosDistintosAlEfectivo();

    String getValidacionActivosNoCorrientes();

    String getValidacionActivos();

    Long getPasivos();

    Long getPasivosCorrientes();

    Long getCuentasPorPagarComercialesProveedores();

    Long getOtrasCuentasPorPagar();

    Long getPasivosPorImpuestos1();

    Long getDeudaFinanciera();

    Long getOtrosPasivosNoFinancieros1();

    Long getDepositosYOtrosActivos2();

    Long getPorcionCorrienteDeuda();

    Long getCuentasPorPagarConPartesRelacionadas3();

    Long getOtrasProvisiones();

    Long getProvisionesPorBeneficiosAEmpleados1();

    Long getIngresosRecibidosPorCuentaDeTerceros();

    String getValidacionPasivosCorrientes();

    Long getPasivosNoCorrientes();

    Long getDeudaALargoPlazo();

    Long getCuentasPorPagar();

    Long getProvisionesPorBeneficiosAEmpleados2();

    Long getPasivosPorImpuestosDiferidos();

    Long getIngresosRecibidosPorAnticipado();

    Long getOtrosPasivosNoFinancieros2();

    Long getOtrosPasivosFinancieros();

    Long getPasivosPorImpuestos2();

    String getValidacionPasivosNoCorrientes();

    String getValidacionPasivos();

    Long getPatrimonio();

    Long getCapitalPagado();

    Long getPrimaDeEmision();

    Long getReadquisicionDeInstrumentosDePatrimonioPropio();

    Long getInversionSumplementariaAlCapitalAsignado();

    Long getOtrasParticipacionesEnElPatrimonio();

    Long getSuperavitPorRevaluacion();

    Long getReservaLegal();

    Long getOtrasReservas();

    Long getUtilidadYorPerdidaDelEjercicio();

    Long getUtilidadYorPerdidaAcumulada();

    Long getGananciasAcumuladasPorEfectoDeLaConvergencia();

    Long getGananciasAcumuladasDiferentesALasGeneradasPorEfectoDeLaConvergencia();

    Long getTotalPasivoPatrimonio();
    String getValidacionPatrimonio();

    String getValidacionGananciaPerdidaNeta();
    String getValidacionEcuacionPatrimonial();
    Integer getNit();
    Integer getIdHeredado();
    Integer getAnnio();
    Boolean getActual();
}


