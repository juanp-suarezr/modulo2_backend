package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFHashDigitoNITProjection {

    @Id
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
