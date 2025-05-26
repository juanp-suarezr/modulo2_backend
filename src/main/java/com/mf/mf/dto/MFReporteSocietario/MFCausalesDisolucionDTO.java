package com.mf.mf.dto.MFReporteSocietario;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFCausalesDisolucionDTO {
    private Long idCausales;
    private Long idConstitucion; // solo el ID de la relación
    private String descripcion;
    private Integer idHeredado;
    private Integer nit;
    private Boolean estado;

}