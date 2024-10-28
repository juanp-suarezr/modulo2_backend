package com.mf.mf.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "MFRequerimiento")
public class MFRequerimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRequerimiento;
    private Integer nombreRequerimiento; // Lista desplegable datos maestros
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaCreacion;
    private Integer periodoEntrega; // Lista desplegable datos maestros
    private Integer tipoProgramacion; // Lista desplegable datos maestros
    private Integer actoAdministrativo; // Input number
    private LocalDate fechaPublicacion;
    private Integer annioVigencia; // Input number
    private byte[] documentoActoAdministrativo;
    private boolean estadoVigilado; // Lista desplegable datos maestros
    //private Integer estadoRequerimiento; // datos maestros asignacion por debajo
    private boolean estado;

}
