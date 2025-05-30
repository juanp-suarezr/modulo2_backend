package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoFlujoDirectoProjection {

    Long getId();
    Boolean getEstado();
    Long getFlujosEfectivoProcedentesUtilizadosActividadesOperacion();
    Long getClasesCobrosActividadesOperacion();
    Long getCobrosVentasBienesPrestacionServicios();
    Long getCobrosRegaliasCuotasComisionesOtrosIngresos();
    Long getCobrosContratosIntermediacionNegociacion();
    Long getCobrosPrimasPrestacionesAnualidadesPolizas();
    Long getCobrosRentasVentasActivosMantenidosTerceros();
    Long getOtrosCobrosPorActividadesOperacion();
    Long getClasesPagosEfectivoActividadesOperacion();
    Long getPagosProveedoresSuministroBienesServicios();
    Long getPagosContratosIntermediacionNegociacion();
    Long getPagosCuentaEmpleados();
    Long getPagosPrimasPrestacionesAnualidadesPolizas();
    Long getPagosActivosMantenidosTercerosVenta();
    Long getOtrosPagosPorActividadesOperacion();
    Long getFlujosEfectivoNetosUtilizadosOperaciones();
    Long getDividendosPagados();
    Long getDividendosRecibidos();
    Long getInteresesPagados();
    Long getInteresesRecibidos();
    Long getImpuestosGananciasReembolsadosPagados();
    Long getOtrasEntradasSalidasEfectivo();
    Long getFlujosEfectivoNetosUtilizadosInversion();
    Long getRecursosCambioPropiedadSubsidiarias();
    Long getPagosCambioPropiedadSubsidiarias();
    Long getImportesEmisionAcciones();
    Long getImportesEmisionInstrumentosPatrimonio();
    Long getPagosAdquirirRescatarAccionesEntidad();
    Long getPagosOtrasParticipacionesPatrimonio();
    Long getImportesPrestamos();
    Long getReembolsosPrestamos();
    Long getPagosPasivosArrendamientosFinancieros();
    Long getImportesSubvencionesGobierno();
    Long getIncrementoDisminucionNetoEfectivo();
    Long getEfectosVariacionTasaCambioEfectivo();
    Long getEfectivoEquivalentesPrincipioPeriodo();
    Long getEfectivoEquivalentesFinalPeriodo();
    Integer getNit();
    Integer getAnnio();
    Boolean getActual();
    Long flujosEfectivoProcedentesUtilizadosFinanciacion();

}


