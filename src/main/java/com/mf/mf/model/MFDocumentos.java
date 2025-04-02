package com.mf.mf.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFDocumentosCargados\"")
public class MFDocumentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocumentos;
    private Integer idHeredado;
    private String nit;
    private String link;
    private boolean estado;

}
