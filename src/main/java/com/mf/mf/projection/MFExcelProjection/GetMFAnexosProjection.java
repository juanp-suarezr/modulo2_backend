package com.mf.mf.projection.MFExcelProjection;

import java.time.LocalDate;

public interface GetMFAnexosProjection {

    long getIdAnexo();
    Integer getIdHeredado();
    String getCaratula();
    String getEstadoSituacionFinanciera();
    String getEstadoResultados();
    String getEstadoResultadosIntegral();
    String getFlujoEfectivo();
    String getEstadoCambiosPatrimonio();
    String getDictamenFiscal();
    String getRevelacionesEstadosFinancieros();
    String getNotasEstadosFinancieros();
    String getCertificacionCumplimientoEEFF();
    String getPoliticasContables();
    String getInformeGestion();
    String getProyectoDistribucionUtilidadesEmpresas();
    String getDeclaracionRenta();
    String getComposicionAccionaria();
    String getActaAsambleaAprobacionEF();
    LocalDate getFechaEntrega();

    Boolean getEstado();

}

