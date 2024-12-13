package com.mf.mf.model.excel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFEstadoFlujoEfectivoDirecto\"")
public class MFEstadoFlujoEfectivoDirecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;
    private Integer flujosEfectivoProcedentesUtilizadosActividadesOperacion;
    private Integer clasesCobrosActividadesOperacion;
    private Integer cobrosVentasBienesPrestacionServicios;
    private Integer cobrosRegaliasCuotasComisionesOtrosIngresos;
    private Integer cobrosContratosIntermediacionNegociacion;
    private Integer cobrosPrimasPrestacionesAnualidadesPolizas;
    private Integer cobrosRentasVentasActivosMantenidosTerceros;
    private Integer otrosCobrosPorActividadesOperacion;
    private Integer clasesPagosEfectivoActividadesOperacion;
    private Integer pagosProveedoresSuministroBienesServicios;
    private Integer pagosContratosIntermediacionNegociacion;
    private Integer pagosCuentaEmpleados;
    private Integer pagosPrimasPrestacionesAnualidadesPolizas;
    private Integer pagosActivosMantenidosTercerosVenta;
    private Integer otrosPagosPorActividadesOperacion;
    private Integer flujosEfectivoNetosUtilizadosOperaciones;
    private Integer dividendosPagados;
    private Integer dividendosRecibidos;
    private Integer interesesPagados;
    private Integer interesesRecibidos;
    private Integer impuestosGananciasReembolsadosPagados;
    private Integer otrasEntradasSalidasEfectivo;
    private Integer flujosEfectivoNetosUtilizadosInversion;
    private Integer recursosCambioPropiedadSubsidiarias;
    private Integer pagosCambioPropiedadSubsidiarias;
    private Integer importesEmisionAcciones;
    private Integer importesEmisionInstrumentosPatrimonio;
    private Integer pagosAdquirirRescatarAccionesEntidad;
    private Integer pagosOtrasParticipacionesPatrimonio;
    private Integer importesPrestamos;
    private Integer reembolsosPrestamos;
    private Integer pagosPasivosArrendamientosFinancieros;
    private Integer importesSubvencionesGobierno;
    private Integer incrementoDisminucionNetoEfectivo;
    private Integer efectosVariacionTasaCambioEfectivo;
    private Integer efectivoEquivalentesPrincipioPeriodo;
    private Integer efectivoEquivalentesFinalPeriodo;
    private Integer nit;
    private Integer annio;
    private Boolean actual;
    
}
