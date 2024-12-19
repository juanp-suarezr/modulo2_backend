package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFRequerimientosEntregasProjection {

    //REQUERIMIENTO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdRequerimiento();

    Integer getNombreRequerimiento();
    Integer getActoAdministrativo();
    LocalDate getFechaInicio();
    LocalDate getFechaFin();
    Integer getAnnioVigencia();

    Integer getEstadoRequerimiento();
    Integer getTipoProgramacion();

    String getTipoRequerimientoDescripcion();
    String getEstadoRequerimientoDescripcion();

    //PROGRAMACION
    Integer getIdProgramacion();

    //HEREDADOS
    Integer getIdHeredado();
    Integer getEstadoEntrega();
    boolean getIndividual();

}
