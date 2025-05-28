package com.mf.mf.dto.MFReporteSocietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MFReunionesDTO {

    private Integer nro_reunion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_reunion;
    private String tipo_reunion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_convocatoria;
    private String direccion;
    private String municipio_id;
    private String medio_informacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_antelacion;
    private Boolean es_asamblea_general;
    private String observaciones;

    private List<MFConvocantesDTO> convocantes;
    private List<MFActasDTO> actas;
}

