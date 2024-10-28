package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFReqeurimientoProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdRequerimiento();

    Integer getNombreRequerimiento();

    LocalDate getFechaInicio();

    LocalDate getFechaFin();

    LocalDate getFechaCreacion();

    Integer getPeriodoEntrega();

    Integer getTipoProgramacion();

    Integer getActoAdministrativo();

    LocalDate getFechaPublicacion();

    Integer getAnnioVigencia();

    byte[] getDocumentoActoAdministrativo();

    boolean getEstadoVigilado();

    // Integer getEstadoRequerimiento();

    boolean getEstado();

}
