package com.mf.mf.dto.MFReporteSocietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MFRevisoresFiscalesDTO {

    private String tipo_identificacion;
    private String nro_identificacion;
    private String nombre;
    private Boolean principal;
    private Boolean activo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_inscripcion;
    private String nro_tarjeta_profesional;

}
