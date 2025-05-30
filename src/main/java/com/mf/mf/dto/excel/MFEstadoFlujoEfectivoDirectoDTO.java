package com.mf.mf.dto.excel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFEstadoFlujoEfectivoDirectoDTO {

    private Long id;
    private Boolean estado;
    private Long flujosEfectivoProcedentesUtilizadosActividadesOperacion;
    private Long clasesCobrosActividadesOperacion;
    private Long cobrosVentasBienesPrestacionServicios;
    private Long cobrosRegaliasCuotasComisionesOtrosIngresos;
    private Long cobrosContratosIntermediacionNegociacion;
    private Long cobrosPrimasPrestacionesAnualidadesPolizas;
    private Long cobrosRentasVentasActivosMantenidosTerceros;
    private Long otrosCobrosPorActividadesOperacion;
    private Long clasesPagosEfectivoActividadesOperacion;
    private Long pagosProveedoresSuministroBienesServicios;
    private Long pagosContratosIntermediacionNegociacion;
    private Long pagosCuentaEmpleados;
    private Long pagosPrimasPrestacionesAnualidadesPolizas;
    private Long pagosActivosMantenidosTercerosVenta;
    private Long otrosPagosPorActividadesOperacion;
    private Long flujosEfectivoNetosUtilizadosOperaciones;
    private Long dividendosPagados;
    private Long dividendosRecibidos;
    private Long interesesPagados;
    private Long interesesRecibidos;
    private Long impuestosGananciasReembolsadosPagados;
    private Long otrasEntradasSalidasEfectivo;
    private Long flujosEfectivoNetosUtilizadosInversion;
    private Long recursosCambioPropiedadSubsidiarias;
    private Long pagosCambioPropiedadSubsidiarias;
    private Long importesEmisionAcciones;
    private Long importesEmisionInstrumentosPatrimonio;
    private Long pagosAdquirirRescatarAccionesEntidad;
    private Long pagosOtrasParticipacionesPatrimonio;
    private Long importesPrestamos;
    private Long reembolsosPrestamos;
    private Long pagosPasivosArrendamientosFinancieros;
    private Long importesSubvencionesGobierno;
    private Long incrementoDisminucionNetoEfectivo;
    private Long efectosVariacionTasaCambioEfectivo;
    private Long efectivoEquivalentesPrincipioPeriodo;
    private Long efectivoEquivalentesFinalPeriodo;
    private Integer idHeredado;
    private Integer nit;

}

