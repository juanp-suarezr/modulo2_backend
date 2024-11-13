package com.mf.mf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFHashHeredadoDTO {

    private long idHeredado;
    private Integer idVigilado; // FK
    private Integer idProgramacion; // FK
    private LocalDate fechaEntrega;
    private boolean estado;
    private Integer estadoEntrega; // datos maestros asignacion por debajo


}
