package com.mf.mf.projection.MFRequerimientoProjection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public interface GetMUVEmpresasProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getId();
    String getNit();
    String getRazonSocial();
    Integer getEstadoAprobacion();
    Boolean getEstado();



}
