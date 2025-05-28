package com.mf.mf.dto.MFReporteSocietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mf.mf.model.MFReporteSocietario.MFReuniones;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MFActasDTO {

    private Integer numeroActa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaActa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInscripcion;

    private Integer nroReunion;

    @Column(length = 100)
    private String temasTratados;

    private Boolean reformaEstatutaria;

}
