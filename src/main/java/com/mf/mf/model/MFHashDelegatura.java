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
    @JoinColumn(name = "\"idDelegatura\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle delegaturaDescripcion;

    @ManyToOne
    @JoinColumn(name = "\"idTipoVigilado\"", referencedColumnName = "id",insertable = false, updatable = false)
    private CatalogoDetalle tipoVigilado;

    @ManyToOne
    @JoinColumn(name = "\"estadoRequerimiento\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle estadoRequerimientos;

    // Relación ManyToOne con MFRequerimiento
    @ManyToOne
    @JoinColumn(name = "\"idRequerimiento\"", referencedColumnName = "idRequerimiento", insertable = false, updatable = false)
    private MFRequerimiento requerimiento;
}
