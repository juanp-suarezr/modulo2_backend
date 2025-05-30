package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoFlujoIndirectoProjection {

    Long getId(); Boolean getEstado();
    Long getGananciaPerdida();
    Long getAjustesGastosImpuestosGanancias();
    Long getAjustesGastosDepreciacionAmortizacion();
    Long getAjustesDeterioroReversionPerdidas();
    Long getAjustesProvisiones();
    Long getAjustesCostosFinancieros();
    Long getAjustesPerdidasGananciasMonedaExtranjera();
    Long getAjustesPerdidasGananciasValorRazonable();
    Long getAjustesGananciasNoDistribuidasAsociadas();
    Long getAjustesPerdidasGananciasDisposicionActivosNoCorrientes();
    Long getOtrosAjustesConciliarGananciaPerdida();
    Long getTotalAjustesConciliarGananciaPerdida();
    Long getAjustesDisminucionesIncrementosInventarios();
    Long getAjustesDisminucionIncrementoCuentasPorCobrar();
    Long getAjustesDisminucionesIncrementosOtrasCuentasCobrar();
    Long getAjustesIncrementoDisminucionCuentasPorPagar();
    Long getAjustesIncrementosDisminucionesOtrasCuentasPagar();
    Long getOtrasEntradasSalidasEfectivo();
    Long getFlujosEfectivoNetosActividadesOperacion();
    Long getFlujosEfectivoPerdidaControlSubsidiarias();
    Long getFlujosEfectivoObtenerControlSubsidiarias();
    Long getCobrosVentaPatrimonioInstrumentosDeuda();
    Long getPagosAdquirirPatrimonioInstrumentosDeuda();
    Long getCobrosVentaParticipacionesNegociosConjuntos();
    Long getPagosAdquirirParticipacionesNegociosConjuntos();
    Long getVentaPropiedadesPlantaEquipo();
    Long getComprasPropiedadesPlantaEquipo();
    Long getVentaActivosIntangibles();
    Long getComprasActivosIntangibles();
    Long getRecursosVentasOtrosActivosLargoPlazo();
    Long getComprasOtrosActivosLargoPlazo();
    Long getAnticiposPrestamosConcedidosTerceros();
    Long getCobrosReembolsoAnticiposPrestamos();
    Long getPagosContratosDerivados();
    Long getCobrosContratosDerivados();
    Long getDividendosRecibidos();
    Long getInteresesRecibidos();
    Long getInteresesPagados();
    Long getFlujosEfectivoNetosActividadesInversion();
    Long getAumentosCapitalRecolocacionAcciones();
    Long getDisminucionCapitalReadquisicionAcciones();
    Long getPagosOtrasParticipacionesPatrimonio();
    Long getAumentoPrimaEmision();
    Long getDisminucionPrimaEmision();
    Long getImportesProcedentesPrestamos();
    Long getReembolsosPrestamos();
    Long getPagosPasivosArrendamientosFinancieros();
    Long getDividendosPagados();
    Long getFlujosEfectivoNetosActividadesFinanciacion();
    Long getIncrementoDisminucionEfectivoEquivalentes();
    Long getEfectosTasaCambioEfectivoEquivalentes();
    Long getEfectivoEquivalentesPrincipioPeriodo();
    Long getEfectivoEquivalentesFinalPeriodo();
    Integer getNit();
    Integer getAnnio();
    Integer getIdHeredado();
    Boolean getActual();

}


