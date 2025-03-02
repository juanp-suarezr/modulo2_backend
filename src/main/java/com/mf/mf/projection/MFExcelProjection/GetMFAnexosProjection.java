package com.mf.mf.projection.MFExcelProjection;

public interface GetMFAnexosProjection {

    Long getIdAnexo();
    Integer getIdHeredado();

    // Si estos archivos deben exponerse como datos binarios (byte[]), puedes dejarlos así.
    byte[] getCaratula();
    byte[] getEstadoSituacionFinanciera();
    byte[] getEstadoResultados();
    byte[] getEstadoResultadosIntegral();
    byte[] getFlujoEfectivoIndirecto();
    byte[] getFlujoEfectivoDirecto();
    byte[] getEstadoCambiosPatrimonio();
    byte[] getDictamenFiscal();

    Boolean getEstado();

}

