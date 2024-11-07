package com.mf.mf.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
    private byte[] documentoActo;
    private Integer estadoVigilado; // Lista desplegable datos maestros
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo
    private Boolean estado;

    // Agregar lista de MFHashDelegaturaDTO
    private List<MFHashDelegaturaDTO> delegaturas;
    // Agregar lista de MFHashDigitoNITDTO
    private List<MFHashDigitoNITDTO> digitoNIT;



}
