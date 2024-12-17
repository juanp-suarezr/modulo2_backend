package com.mf.mf.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFHashHeredados\"")
public class MFHashHeredado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHeredado;
    private Integer idVigilado; // FK
    private long idProgramacion; // FK
    private LocalDate fechaEntrega;
    private boolean estado;
    private Integer estadoEntrega; // datos maestros asignacion por debajo
    private Integer nit;

    // Relaciones con otras entidades

    @ManyToOne
    @JoinColumn(name = "\"estadoEntrega\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle estadoEntregaDescripcion;

    // Relación ManyToOne con MFRequerimiento
    @ManyToOne
    @JoinColumn(name = "\"idProgramacion\"", referencedColumnName = "idProgramacion", insertable = false, updatable = false)
    private MFHashDelegatura delegatura;

    // Relación ManyToOne con MFRequerimiento
    @ManyToOne
    @JoinColumn(name = "\"idProgramacion\"", referencedColumnName = "idProgramacion", insertable = false, updatable = false)
    private MFHashDigitoNIT digitoNIT;
}
