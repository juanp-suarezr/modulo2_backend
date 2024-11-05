package com.mf.mf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFProgramacionDigitoNIT\"")
public class MFHashDigitoNIT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProgramacion;
    private Integer idNumeroDigitos; // datos maestros
    private String finRango; // rangos
    private LocalDate fechaFin;
    private long idRequerimiento; // FK
    private boolean estado;
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo

    // Relaciones con otras entidades
    @ManyToOne
    @JoinColumn(name = "\"idNumeroDigitos\"", insertable = false, updatable = false)
    @JsonIgnore
    private CatalogoDetalle NumeroDigitos;

    @ManyToOne
    @JoinColumn(name = "\"estadoRequerimiento\"", insertable = false, updatable = false)
    @JsonIgnore
    private CatalogoDetalle estadoRequerimientos;

//    @ManyToOne
//    @JoinColumn(name = "\"idRequerimiento\"", insertable = false, updatable = false)
//    @JsonIgnore
//    private Requerimiento requerimiento;
}
