package com.mf.mf.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "muvbck", name = "\"MUVEmpresa\"")
public class MUVEmpresas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nit;
    private String razonSocial;
    private boolean estado;

}
