package com.mf.mf.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MFRequerimientoDTO {

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
