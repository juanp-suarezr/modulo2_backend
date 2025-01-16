package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoFlujoIndirectoProjection {

    Long getId(); Boolean getEstado();
    Integer getGananciaPerdida();
    Integer getAjustesGastosImpuestosGanancias();
    Integer getAjustesGastosDepreciacionAmortizacion();
    Integer getAjustesDeterioroReversionPerdidas();
    Integer getAjustesProvisiones();
    Integer getAjustesCostosFinancieros();
    Integer getAjustesPerdidasGananciasMonedaExtranjera();
    Integer getAjustesPerdidasGananciasValorRazonable();
    Integer getAjustesGananciasNoDistribuidasAsociadas();
    Integer getAjustesPerdidasGananciasDisposicionActivosNoCorrientes();
    Integer getOtrosAjustesConciliarGananciaPerdida();
    Integer getTotalAjustesConciliarGananciaPerdida();
    Integer getAjustesDisminucionesIncrementosInventarios();
    Integer getAjustesDisminucionIncrementoCuentasPorCobrar();
    Integer getAjustesDisminucionesIncrementosOtrasCuentasCobrar();
    Integer getAjustesIncrementoDisminucionCuentasPorPagar();
    Integer getAjustesIncrementosDisminucionesOtrasCuentasPagar();
    Integer getOtrasEntradasSalidasEfectivo();
    Integer getFlujosEfectivoNetosActividadesOperacion();
    Integer getFlujosEfectivoPerdidaControlSubsidiarias();
    Integer getFlujosEfectivoObtenerControlSubsidiarias();
    Integer getCobrosVentaPatrimonioInstrumentosDeuda();
    Integer getPagosAdquirirPatrimonioInstrumentosDeuda();
    Integer getCobrosVentaParticipacionesNegociosConjuntos();
    Integer getPagosAdquirirParticipacionesNegociosConjuntos();
    Integer getVentaPropiedadesPlantaEquipo();
    Integer getComprasPropiedadesPlantaEquipo();
    Integer getVentaActivosIntangibles();
    Integer getComprasActivosIntangibles();
    Integer getRecursosVentasOtrosActivosLargoPlazo();
    Integer getComprasOtrosActivosLargoPlazo();
    Integer getAnticiposPrestamosConcedidosTerceros();
    Integer getCobrosReembolsoAnticiposPrestamos();
    Integer getPagosContratosDerivados();
    Integer getCobrosContratosDerivados();
    Integer getDividendosRecibidos();
    Integer getInteresesRecibidos();
    Integer getInteresesPagados();
    Integer getFlujosEfectivoNetosActividadesInversion();
    Integer getAumentosCapitalRecolocacionAcciones();
    Integer getDisminucionCapitalReadquisicionAcciones();
    Integer getPagosOtrasParticipacionesPatrimonio();
    Integer getAumentoPrimaEmision();
    Integer getDisminucionPrimaEmision();
    Integer getImportesProcedentesPrestamos();
    Integer getReembolsosPrestamos();
    Integer getPagosPasivosArrendamientosFinancieros();
    Integer getDividendosPagados();
    Integer getFlujosEfectivoNetosActividadesFinanciacion();
    Integer getIncrementoDisminucionEfectivoEquivalentes();
    Integer getEfectosTasaCambioEfectivoEquivalentes();
    Integer getEfectivoEquivalentesPrincipioPeriodo();
    Integer getEfectivoEquivalentesFinalPeriodo();
    Integer getNit();
    Integer getAnnio();
    Integer getIdHeredado();
    Boolean getActual();

}


