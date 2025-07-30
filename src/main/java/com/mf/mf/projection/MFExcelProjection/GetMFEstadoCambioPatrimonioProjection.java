package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoCambioPatrimonioProjection {

    Long getId();
    Boolean getEstado();
    Long getSaldoInicialPeriodoActual();
    Long getDisminucionIncrementoDistribucionesPropietarios();
    Long getIncrementoDisminucionTransferenciasOtrosCambios();
    Long getIncrementoDisminucionTransaccionesAccionesCartera();
    Long getIncrementoDisminucionCambiosParticipacionSubsidiarias();
    Long getTotalCambiosPatrimonio();
    Long getSaldoFinalPeriodoActual();
    Long getIncrementoDisminucionPoliticasContables();
    Long getIncrementoDisminucionCorreccionesErrores();
    Long getSaldoInicialReexpresado();
    Long getCambiosEnPatrimonio();
    Long getResultadoIntegral();
    Long getGananciaPerdida();
    Long getOtroResultadoIntegral();
    Long getResultadoIntegralFinal();
    Long getEmisionPatrimonio();
    Long getDividendos();
    Long getIncrementoDisminucionAportacionesPropietarios();

    Integer getNit();
    Integer getAnnio();
    Boolean getActual();

}


