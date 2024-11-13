package com.mf.mf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MUVTipoVigiladoDTO {

    private long id;
    private Integer id_grupo;
    private String sigla;
    private String descripcion;
    private boolean estado;
    private Integer idDelegatura;


}
