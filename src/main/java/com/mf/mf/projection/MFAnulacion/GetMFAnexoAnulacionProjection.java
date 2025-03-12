package com.mf.mf.projection.MFAnulacion;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

public interface GetMFAnexoAnulacionProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getId();
    String getNombre();
    String getDetalle();
    String getPath();

    Boolean getEstado();


}
