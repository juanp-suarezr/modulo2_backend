package com.mf.mf.model.excel;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFDocumentosVigilado\"")
public class MFDocumentosVigilado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;
    private String tipo;
    private String nit;
    private byte[] documento;


}

