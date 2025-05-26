package com.mf.mf.projection.MFReporteSocietario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFCausalesDisolucionProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long getIdCausales();
    Long getIdConstitucion();
    String getDescripcion();
    Integer getIdHeredado();
    Integer getNit();
    Boolean getEstado();

}
