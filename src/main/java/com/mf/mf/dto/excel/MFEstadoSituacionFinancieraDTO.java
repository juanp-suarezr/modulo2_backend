package com.mf.mf.dto.excel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFEstadoSituacionFinancieraDTO {

    private Long id;
    private Boolean estado;
    private Integer totalActivosCorrientes;
    private Integer efectivoYEquivalentesAlEfectivo;
    private Integer efectivoRestringido;
    private Integer inversionesCortoPlazo;
    private Integer cuentasComercialesCobrarOperacionalesClientes;
    private Integer cuentasPorCobrarConPartesRelacionadas1;
    private Integer activosBiologicos;
    private Integer otrasCuentasPorCobrar1;
    private Integer pagosAnticipados;
    private Integer inventariosCorrientes;
    private Integer activosPorImpuestos;
    private Integer activosDistintosAlEfectivoPignoradosComoGarantia;
    private Integer otrosActivosFinancieros1;
    private Integer otrosActivosNoFinancieros1;
    private String validacionActivosCorrientes;

    private Integer totalActivosNoCorrientes;
    private Integer depositosYOtrosActivos1;
    private Integer inversionesLargoPlazo;
    private Integer cuentasComercialesPorCobrar;
    private Integer cuentasPorCobrarConPartesRelacionadas2;
    private Integer otrasCuentasPorCobrar2;
    private Integer propiedadDeInversion;
    private Integer activosIntangiblesYCreditoMercantil;
    private Integer propiedadesPlantaYEquipo;
    private Integer activosBiologicosNoCorrientes;
    private Integer inversionesContabilizadasParticipacion;
    private Integer inversionesSubsidiarNegocios;
    private Integer plusvalia;
    private Integer inventariosNoCorrientes;
    private Integer activosPorImpuestosDiferidos;
    private Integer activosPorImpuestosCorrientesNoCorrientes;
    private Integer otrosActivosFinancieros2;
    private Integer otrosActivosNoFinancieros2;
    private Integer activosDistintosAlEfectivo;
    private String validacionActivosNoCorrientes;
    private String validacionActivos;

    private Integer pasivos;
    private Integer pasivosCorrientes;
    private Integer cuentasPorPagarComercialesProveedores;
    private Integer otrasCuentasPorPagar;
    private Integer pasivosPorImpuestos1;
    private Integer deudaFinanciera;
    private Integer otrosPasivosNoFinancieros1;
    private Integer depositosYOtrosActivos2;
    private Integer porcionCorrienteDeuda;
    private Integer cuentasPorPagarConPartesRelacionadas3;
    private Integer otrasProvisiones;
    private Integer provisionesPorBeneficiosAEmpleados1;
    private Integer ingresosRecibidosPorCuentaDeTerceros;
    private String validacionPasivosCorrientes;

    private Integer pasivosNoCorrientes;
    private Integer deudaALargoPlazo;
    private Integer cuentasPorPagar;
    private Integer provisionesPorBeneficiosAEmpleados2;
    private Integer pasivosPorImpuestosDiferidos;
    private Integer ingresosRecibidosPorAnticipado;
    private Integer otrosPasivosNoFinancieros2;
    private Integer otrosPasivosFinancieros;
    private Integer pasivosPorImpuestos2;
    private String validacionPasivosNoCorrientes;

    private String validacionPasivos;
    private Integer patrimonio;
    private Integer capitalPagado;
    private Integer primaDeEmision;
    private Integer readquisicionDeInstrumentosDePatrimonioPropio;
    private Integer inversionSumplementariaAlCapitalAsignado;
    private Integer otrasParticipacionesEnElPatrimonio;
    private Integer superavitPorRevaluacion;
    private Integer reservaLegal;
    private Integer otrasReservas;
    private Integer utilidadYorPerdidaDelEjercicio;
    private Integer utilidadYorPerdidaAcumulada;
    private Integer gananciasAcumuladasPorEfectoDeLaConvergencia;
    private Integer gananciasAcumuladasDiferentesALasGeneradasPorEfectoDeLaConvergencia;
    private String validacionPatrimonio;
    private String validacionGananciaPerdidaNeta;

}

