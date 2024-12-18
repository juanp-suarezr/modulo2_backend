package com.mf.mf.projection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFHashDigitoNITMUVProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdProgramacion();
    Integer getIdNumeroDigitos();
    String getInicioRango();
    String getFinRango();
    LocalDate getFechaFin();
    Integer getEstadoRequerimiento();
    Boolean getEstado();


}
