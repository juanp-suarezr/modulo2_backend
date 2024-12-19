package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

public interface GetMFHashHeredadosProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdHeredado();
    Integer getIdVigilado();
    Integer getIdProgramacion();
    LocalDate getFechaEntrega();
    Integer getEstadoRequerimiento();
    Boolean getEstado();
    Boolean getIndividual();
    Integer getTipoProgramacion();

}
