package com.mf.mf.dto.excel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFDictamenRevisorFiscalDTO {

    private Long id;
    private Boolean estado;
    private String dictamen;
    private String opinionDictamen;
    private String contenidoSalvedadDictamen;
    private String parrafoEnfasisDictamen;
    private String hipotesisNegocioMarcha;
    private String validacionHNM;
    private Long nit;
    private Integer annio;

}

