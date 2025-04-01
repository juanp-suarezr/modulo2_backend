package com.mf.mf.dto.excel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFEstadoSituacionFinancieraDTO {

    private Long id;
    private Boolean estado;
    private Long totalActivosCorrientes;
    private Long efectivoYEquivalentesAlEfectivo;
    private Long efectivoRestringido;
    private Long inversionesCortoPlazo;
    private Long cuentasComercialesCobrarOperacionalesClientes;
    private Long cuentasPorCobrarConPartesRelacionadas1;
    private Long activosBiologicos;
    private Long otrasCuentasPorCobrar1;
    private Long pagosAnticipados;
    private Long inventariosCorrientes;
    private Long activosPorImpuestos;
    private Long activosDistintosAlEfectivoPignoradosComoGarantia;
    private Long otrosActivosFinancieros1;
    private Long otrosActivosNoFinancieros1;
    private String validacionActivosCorrientes;

    private Long totalActivosNoCorrientes;
    private Long depositosYOtrosActivos1;
    private Long inversionesLargoPlazo;
    private Long cuentasComercialesPorCobrar;
    private Long cuentasPorCobrarConPartesRelacionadas2;
    private Long otrasCuentasPorCobrar2;
    private Long propiedadDeInversion;
    private Long activosIntangiblesYCreditoMercantil;
    private Long propiedadesPlantaYEquipo;
    private Long activosBiologicosNoCorrientes;
    private Long inversionesContabilizadasParticipacion;
    private Long inversionesSubsidiarNegocios;
    private Long plusvalia;
    private Long inventariosNoCorrientes;
    private Long activosPorImpuestosDiferidos;
    private Long activosPorImpuestosCorrientesNoCorrientes;
    private Long otrosActivosFinancieros2;
    private Long otrosActivosNoFinancieros2;
    private Long activosDistintosAlEfectivo;
    private String validacionActivosNoCorrientes;
    private String validacionActivos;

    private Long pasivos;
    private Long pasivosCorrientes;
    private Long cuentasPorPagarComercialesProveedores;
    private Long otrasCuentasPorPagar;
    private Long pasivosPorImpuestos1;
    private Long deudaFinanciera;
    private Long otrosPasivosNoFinancieros1;
    private Long depositosYOtrosActivos2;
    private Long porcionCorrienteDeuda;
    private Long cuentasPorPagarConPartesRelacionadas3;
    private Long otrasProvisiones;
    private Long provisionesPorBeneficiosAEmpleados1;
    private Long ingresosRecibidosPorCuentaDeTerceros;
    private String validacionPasivosCorrientes;

    private Long pasivosNoCorrientes;
    private Long deudaALargoPlazo;
    private Long cuentasPorPagar;
    private Long provisionesPorBeneficiosAEmpleados2;
    private Long pasivosPorImpuestosDiferidos;
    private Long ingresosRecibidosPorAnticipado;
    private Long otrosPasivosNoFinancieros2;
    private Long otrosPasivosFinancieros;
    private Long pasivosPorImpuestos2;
    private String validacionPasivosNoCorrientes;

    private String validacionPasivos;
    private Long patrimonio;
    private Long capitalPagado;
    private Long primaDeEmision;
    private Long readquisicionDeInstrumentosDePatrimonioPropio;
    private Long inversionSumplementariaAlCapitalAsignado;
    private Long otrasParticipacionesEnElPatrimonio;
    private Long superavitPorRevaluacion;
    private Long reservaLegal;
    private Long otrasReservas;
    private Long utilidadYorPerdidaDelEjercicio;
    private Long utilidadYorPerdidaAcumulada;
    private Long gananciasAcumuladasPorEfectoDeLaConvergencia;
    private Long gananciasAcumuladasDiferentesALasGeneradasPorEfectoDeLaConvergencia;
    private String validacionPatrimonio;
    private String validacionGananciaPerdidaNeta;
    private Integer idHeredado;
    private Integer nit;
}

