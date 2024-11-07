package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

public interface GetMFRequerimientosTableProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdRequerimiento();

    Integer getNombreRequerimiento();
    Integer getActoAdministrativo();
    LocalDate getFechaInicio();
    LocalDate getFechaFin();
    Integer getAnnioVigencia();

    Integer getEstadoRequerimiento();

    Boolean getEstado();

    String getTipoRequerimientoDescripcion();
    String getEstadoRequerimientoDescripcion();


}
