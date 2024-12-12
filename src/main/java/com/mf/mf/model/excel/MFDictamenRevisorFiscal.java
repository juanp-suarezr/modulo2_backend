package com.mf.mf.model.excel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFDictamenRevisorFiscal\"")
public class MFDictamenRevisorFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;
    private String dictamen;
    private String opinionDictamen;
    private String contenidoSalvedadDictamen;
    private String parrafoEnfasisDictamen;
    private String hipotesisNegocioMarcha;
    private String validacionHNM;
    private Integer nit;
    private Integer annio;
//    private Boolean actual;
    
}
