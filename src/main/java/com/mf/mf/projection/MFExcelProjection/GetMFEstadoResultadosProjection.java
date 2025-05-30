package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoResultadosProjection {

    Long getId();

    Boolean getEstado();

    Long getResultadoIntegralPropietarios();
    Long getResultadoIntegralNoControladoras();
    Long getResultadoIntegralTotal();
    String getValidacionEstadoResultados();
    Long getIngresosActividadesOrdinarias();
    Long getIngresosActividadesTransporte();
    Long getIngresosOtrasActividades();
    Long getCostoVentas();
    Long getAmortizacion();
    Long getDepreciacion();
    Long getGananciaBruta();
    Long getGastosVentas();
    Long getGastosAdministracion();
    Long getOtrosGastosOperacionales();
    Long getOtrosIngresosOperacionales();
    Long getOtrasGananciasPerdidasOperacionales();
    Long getGananciaOperacion();
    Long getDiferenciaDividendosActivos();
    Long getGananciaPosicionMonetariaNeta();
    Long getGananciasBajaActivosFinancieros();
    Long getIngresosNoOperacionales();
    Long getIngresosFinancieros();
    Long getOtrosIngresosNoOperacionales();
    Long getGastosNoOperacionales();
    Long getCostosFinancieros();
    Long getOtrosGastosFinancieros();
    Long getInteresesDeuda();
    Long getOtrosGastosNoOperacionales();
    Long getParticipacionGananciasAsociadas();
    Long getGananciaAntesImpuestos();
    Long getIngresoImpuestos();
    Long getGastoImpuestoGanancias();
    Long getGananciaOperacionesContinuadas();
    Long getGananciaOperacionesDiscontinuadas();
    Long getGananciaNeta();
    Integer getNit();
    Integer getIdHeredado();
    Integer getAnnio();
    Boolean getActual();
}


