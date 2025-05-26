package com.mf.mf.projection.MFReporteSocietario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFReporteSocietarioProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdSocietario();
    String getTipoSociedad();
    String getTipoDocumentoCreacion();
    Integer getCiudadNotariaId();
    String getNotaria();
    LocalDate getFechaDocumento();
    String getRegistroMercantil();
    LocalDate getFechaRegistroMercantil();
    String getCiudadCamaraComercio();
    String getNombreCamaraComercio();
    Boolean getVigenciaIndefinida();
    LocalDate getFechaVigencia();
    Boolean getEstado();
    Integer getIdHeredado();
    Integer getNit();


}
