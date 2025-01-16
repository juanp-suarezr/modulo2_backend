package com.mf.mf.projection.MFExcelProjection;

import java.time.LocalDate;

public interface GetMFDictamenRevisorFiscalProjection {

    Long getId();
    Boolean getEstado();
    String getDictamen();
    String getOpinionDictamen();
    String getContenidoSalvedadDictamen();
    String getParrafoEnfasisDictamen();
    String getHipotesisNegocioMarcha();
    String getValidacionHNM();
    Integer getNit();
    Integer getAnnio();
    Boolean getActual();

}


