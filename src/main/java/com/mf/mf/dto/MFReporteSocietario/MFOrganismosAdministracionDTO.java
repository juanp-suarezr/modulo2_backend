package com.mf.mf.dto.MFReporteSocietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MFOrganismosAdministracionDTO {

    private String tipo_identificacion;
    private String nro_identificacion;
    private String nombres;
    private String apellidos;
    private String tipo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_inscripcion;
    private Boolean activo;
    private String nro_tarjeta_profesional;
    private String calidad;

}
