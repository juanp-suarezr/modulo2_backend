package com.mf.mf.dto.anulacion;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFAnexoAnulacionDTO {

    private long id;
    private Integer idAnulacion; // FK
    private String nombre;
    private String detalle;
    private String path;
    private boolean estado;



}
