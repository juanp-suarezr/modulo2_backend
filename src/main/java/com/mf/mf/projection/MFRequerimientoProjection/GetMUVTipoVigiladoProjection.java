package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public interface GetMUVTipoVigiladoProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getId();
    Integer getIdGrupo();
    String getSigla();
    String getDescripcion();
    Integer getIdDelegatura();
    Boolean getEstado();



}
