package com.mf.mf.projection.MFExcelProjection;

import java.time.LocalDate;

public interface GetMFEstadoSituacionFinancieraProjection {

    Long getId();

    Boolean getEstado();

    Integer getTotalActivosCorrientes();

    Integer getEfectivoYEquivalentesAlEfectivo();

    Integer getEfectivoRestringido();

    Integer getInversionesCortoPlazo();

    Integer getCuentasComercialesCobrarOperacionalesClientes();

    Integer getCuentasPorCobrarConPartesRelacionadas1();

    Integer getActivosBiologicos();

    Integer getOtrasCuentasPorCobrar1();

    Integer getPagosAnticipados();

    Integer getInventariosCorrientes();

    Integer getActivosPorImpuestos();

    Integer getActivosDistintosAlEfectivoPignoradosComoGarantia();

    Integer getOtrosActivosFinancieros1();

    Integer getOtrosActivosNoFinancieros1();

    String getValidacionActivosCorrientes();

    Integer getTotalActivosNoCorrientes();

    Integer getDepositosYOtrosActivos1();

    Integer getInversionesLargoPlazo();

    Integer getCuentasComercialesPorCobrar();

    Integer getCuentasPorCobrarConPartesRelacionadas2();

    Integer getOtrasCuentasPorCobrar2();

    Integer getPropiedadDeInversion();

    Integer getActivosIntangiblesYCreditoMercantil();

    Integer getPropiedadesPlantaYEquipo();

    Integer getActivosBiologicosNoCorrientes();

    Integer getInversionesContabilizadasParticipacion();

    Integer getInversionesSubsidiarNegocios();

    Integer getPlusvalia();

    Integer getInventariosNoCorrientes();

    Integer getActivosPorImpuestosDiferidos();

    Integer getActivosPorImpuestosCorrientesNoCorrientes();

    Integer getOtrosActivosFinancieros2();

    Integer getOtrosActivosNoFinancieros2();

    Integer getActivosDistintosAlEfectivo();

    String getValidacionActivosNoCorrientes();

    String getValidacionActivos();

    Integer getPasivos();

    Integer getPasivosCorrientes();

    Integer getCuentasPorPagarComercialesProveedores();

    Integer getOtrasCuentasPorPagar();

    Integer getPasivosPorImpuestos1();

    Integer getDeudaFinanciera();

    Integer getOtrosPasivosNoFinancieros1();

    Integer getDepositosYOtrosActivos2();

    Integer getPorcionCorrienteDeuda();

    Integer getCuentasPorPagarConPartesRelacionadas3();

    Integer getOtrasProvisiones();

    Integer getProvisionesPorBeneficiosAEmpleados1();

    Integer getIngresosRecibidosPorCuentaDeTerceros();

    String getValidacionPasivosCorrientes();

    Integer getPasivosNoCorrientes();

    Integer getDeudaALargoPlazo();

    Integer getCuentasPorPagar();

    Integer getProvisionesPorBeneficiosAEmpleados2();

    Integer getPasivosPorImpuestosDiferidos();

    Integer getIngresosRecibidosPorAnticipado();

    Integer getOtrosPasivosNoFinancieros2();

    Integer getOtrosPasivosFinancieros();

    Integer getPasivosPorImpuestos2();

    String getValidacionPasivosNoCorrientes();

    String getValidacionPasivos();

    Integer getPatrimonio();

    Integer getCapitalPagado();

    Integer getPrimaDeEmision();

    Integer getReadquisicionDeInstrumentosDePatrimonioPropio();

    Integer getInversionSumplementariaAlCapitalAsignado();

    Integer getOtrasParticipacionesEnElPatrimonio();

    Integer getSuperavitPorRevaluacion();

    Integer getReservaLegal();

    Integer getOtrasReservas();

    Integer getUtilidadYorPerdidaDelEjercicio();

    Integer getUtilidadYorPerdidaAcumulada();

    Integer getGananciasAcumuladasPorEfectoDeLaConvergencia();

    Integer getGananciasAcumuladasDiferentesALasGeneradasPorEfectoDeLaConvergencia();

    Integer getTotalPasivoPatrimonio();
    String getValidacionPatrimonio();

    String getValidacionGananciaPerdidaNeta();
    String getValidacionEcuacionPatrimonial();
    Integer getNit();
    Integer getIdHeredado();
    Integer getAnnio();
    Boolean getActual();
}


