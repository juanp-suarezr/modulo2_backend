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
    private Integer nit;
    private Integer annio;
    private Boolean actual;
    private Integer idHeredado;
    private Long flujosEfectivoProcedentesUtilizadosFinanciacion;

}
