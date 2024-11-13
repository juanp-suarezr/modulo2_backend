package com.mf.mf.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "muv", name = "\"MUVTipoVigilado\"")
public class MUVTipoVigilado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer id_grupo;
    private String sigla;
    private String descripcion;
    private boolean estado;
    private Integer idDelegatura;


}
