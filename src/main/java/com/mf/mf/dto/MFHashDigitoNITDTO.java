package com.mf.mf.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MFHashDigitoNITDTO {

    private long idProgramacion;
    private Integer idNumeroDigitos; // datos maestros
    private String inicioRango; // rangos
    private String finRango; // rangos
    private LocalDate fechaFin;
    private long idRequerimiento; // FK
    private boolean estado;
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo
    private String digitoUnico;
    private List<MFVigiladoDTO> vigilados; // Nueva lista con ID y NIT de los vigilados

}
