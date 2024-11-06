package com.mf.mf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFHashDelegaturaDTOO {

    private long idProgramacion;
    private Integer idDelegatura; // Lista desplegable datos maestros
    private Integer idTipoVigilado; // Lista desplegable datos maestros
    private long idRequerimiento; // FK
    private LocalDate fechaFin;
    private boolean estado;
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo


}
