package com.mf.mf.dto.MFReporteSocietario;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MFDatosAdicionalesDTO {

    private String distribucion_utilidades;
    private String duracion_sociedad;
    private LocalDate fecha_convocatoria;
    private String forma_convocatoria;
    private String organos_decision;
    private Boolean estado = true;

    private Integer idHeredado;
    private Integer nit;
}
