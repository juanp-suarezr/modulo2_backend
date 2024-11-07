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
    private String inicioRango; // rangos
    private String finRango; // rangos
    private LocalDate fechaFin;
    private long idRequerimiento; // FK
    private boolean estado;
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo

    // Relaciones con otras entidades
    @ManyToOne
    @JoinColumn(name = "\"idNumeroDigitos\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle NumeroDigitos;

    @ManyToOne
    @JoinColumn(name = "\"estadoRequerimiento\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle estadoRequerimientos;

    // Relación ManyToOne con MFRequerimiento
    @ManyToOne
    @JoinColumn(name = "\"idRequerimiento\"", referencedColumnName = "idRequerimiento", insertable = false, updatable = false)
    private MFRequerimiento requerimiento;

}
