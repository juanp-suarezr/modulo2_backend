package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

public interface GetMFHashDigitoNITProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdProgramacion();
    Integer getIdNumeroDigitos();
    String getInicioRango();
    String getFinRango();
    LocalDate getFechaFin();
    long getIdRequerimiento();
    Integer getEstadoRequerimiento();
    Boolean getEstado();
    String getDigitoUnico();

//    String getEstadoRequerimientos();
//
//    String getNumeroDigitos();

}
