package com.mf.mf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFProgramacionDelegatura\"")
public class MFHashDelegatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProgramacion;
    private Integer idDelegatura; // Lista desplegable datos maestros
    private Integer idTipoVigilado; // Lista desplegable datos maestros
    private long idRequerimiento; // FK
    private LocalDate fechaFin;
    private boolean estado;
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo

    // Relaciones con otras entidades
    @ManyToOne
    @JoinColumn(name = "\"idDelegatura\"", insertable = false, updatable = false)
    @JsonIgnore
    private CatalogoDetalle delegatura;

    @ManyToOne
    @JoinColumn(name = "\"idTipoVigilado\"", insertable = false, updatable = false)
    @JsonIgnore
    private CatalogoDetalle tipoVigilado;

    @ManyToOne
    @JoinColumn(name = "\"estadoRequerimiento\"", insertable = false, updatable = false)
    @JsonIgnore
    private CatalogoDetalle estadoRequerimientos;

//    @ManyToOne
//    @JoinColumn(name = "\"idRequerimiento\"", insertable = false, updatable = false)
//    @JsonIgnore
//    private Requerimiento requerimiento;
}
