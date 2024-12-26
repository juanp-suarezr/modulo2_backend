package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoResultadosProjection {

    Long getId();

    Boolean getEstado();

    Integer getResultadoIntegralPropietarios();
    Integer getResultadoIntegralNoControladoras();
    Integer getResultadoIntegralTotal();
    String getValidacionEstadoResultados();
    Integer getIngresosActividadesOrdinarias();
    Integer getIngresosActividadesTransporte();
    Integer getIngresosOtrasActividades();
    Integer getCostoVentas();
    Integer getAmortizacion();
    Integer getDepreciacion();
    Integer getGananciaBruta();
    Integer getGastosVentas();
    Integer getGastosAdministracion();
    Integer getOtrosGastosOperacionales();
    Integer getOtrosIngresosOperacionales();
    Integer getOtrasGananciasPerdidasOperacionales();
    Integer getGananciaOperacion();
    Integer getDiferenciaDividendosActivos();
    Integer getGananciaPosicionMonetariaNeta();
    Integer getGananciasBajaActivosFinancieros();
    Integer getIngresosNoOperacionales();
    Integer getIngresosFinancieros();
    Integer getOtrosIngresosNoOperacionales();
    Integer getGastosNoOperacionales();
    Integer getCostosFinancieros();
    Integer getOtrosGastosFinancieros();
    Integer getInteresesDeuda();
    Integer getOtrosGastosNoOperacionales();
    Integer getParticipacionGananciasAsociadas();
    Integer getGananciaAntesImpuestos();
    Integer getIngresoImpuestos();
    Integer getGastoImpuestoGanancias();
    Integer getGananciaOperacionesContinuadas();
    Integer getGananciaOperacionesDiscontinuadas();
    Integer getGananciaNeta();
    Integer getNit();
    Integer getIdHeredado();
    Integer getAnnio();
    Boolean getActual();
}


