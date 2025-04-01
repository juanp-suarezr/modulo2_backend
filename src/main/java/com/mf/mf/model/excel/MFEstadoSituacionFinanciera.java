package com.mf.mf.model.excel;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFEstadoSituacionFinanciera\"")
public class MFEstadoSituacionFinanciera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String validacionActivosNoCorrientes; // Cambiado a String (tipo texto)
    private String validacionActivos; // Cambiado a String (tipo texto)

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
    private String validacionPasivosCorrientes; // Cambiado a String (tipo texto)

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
    private String validacionPasivos; // Añadido

    private Long patrimonio; // Añadido
    private Long capitalPagado; // Añadido
    private Long primaDeEmision; // Añadido
    private Long readquisicionDeInstrumentosDePatrimonioPropio; // Añadido
    private Long inversionSumplementariaAlCapitalAsignado; // Añadido
    private Long otrasParticipacionesEnElPatrimonio; // Añadido
    private Long superavitPorRevaluacion; // Añadido
    private Long reservaLegal; // Añadido
    private Long otrasReservas; // Añadido
    private Long utilidadYorPerdidaDelEjercicio; // Añadido
    private Long utilidadYorPerdidaAcumulada; // Añadido
    private Long gananciasAcumuladasPorEfectoDeLaConvergencia; // Añadido
    private Long gananciasAcumuladasDiferentesALasGeneradasPorEfectoDeLaConvergencia; // Añadido
    private String validacionPatrimonio; // Añadido
    private String validacionGananciaPerdidaNeta; // Añadido
    private Long totalPasivoPatrimonio;
    private String validacionEcuacionPatrimonial;
    private Integer nit;
    private Integer annio;
    private Boolean actual;
    private Integer idHeredado;


}

