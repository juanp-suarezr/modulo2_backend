package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFHashDelegaturaProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdProgramacion();
    Integer getIdDelegatura();
    Integer getIdTipoVigilado();
    LocalDate getFechaFin();
    long getIdRequerimiento();
    Integer getEstadoRequerimiento();
    Boolean getEstado();

}
