package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoFlujoDirectoProjection {

    Long getId();
    Boolean getEstado();
    Integer getFlujosEfectivoProcedentesUtilizadosActividadesOperacion();
    Integer getClasesCobrosActividadesOperacion();
    Integer getCobrosVentasBienesPrestacionServicios();
    Integer getCobrosRegaliasCuotasComisionesOtrosIngresos();
    Integer getCobrosContratosIntermediacionNegociacion();
    Integer getCobrosPrimasPrestacionesAnualidadesPolizas();
    Integer getCobrosRentasVentasActivosMantenidosTerceros();
    Integer getOtrosCobrosPorActividadesOperacion();
    Integer getClasesPagosEfectivoActividadesOperacion();
    Integer getPagosProveedoresSuministroBienesServicios();
    Integer getPagosContratosIntermediacionNegociacion();
    Integer getPagosCuentaEmpleados();
    Integer getPagosPrimasPrestacionesAnualidadesPolizas();
    Integer getPagosActivosMantenidosTercerosVenta();
    Integer getOtrosPagosPorActividadesOperacion();
    Integer getFlujosEfectivoNetosUtilizadosOperaciones();
    Integer getDividendosPagados();
    Integer getDividendosRecibidos();
    Integer getInteresesPagados();
    Integer getInteresesRecibidos();
    Integer getImpuestosGananciasReembolsadosPagados();
    Integer getOtrasEntradasSalidasEfectivo();
    Integer getFlujosEfectivoNetosUtilizadosInversion();
    Integer getRecursosCambioPropiedadSubsidiarias();
    Integer getPagosCambioPropiedadSubsidiarias();
    Integer getImportesEmisionAcciones();
    Integer getImportesEmisionInstrumentosPatrimonio();
    Integer getPagosAdquirirRescatarAccionesEntidad();
    Integer getPagosOtrasParticipacionesPatrimonio();
    Integer getImportesPrestamos();
    Integer getReembolsosPrestamos();
    Integer getPagosPasivosArrendamientosFinancieros();
    Integer getImportesSubvencionesGobierno();
    Integer getIncrementoDisminucionNetoEfectivo();
    Integer getEfectosVariacionTasaCambioEfectivo();
    Integer getEfectivoEquivalentesPrincipioPeriodo();
    Integer getEfectivoEquivalentesFinalPeriodo();
    Integer getNit();
    Integer getAnnio();
    Boolean getActual();
    Integer flujosEfectivoProcedentesUtilizadosFinanciacion();

}


