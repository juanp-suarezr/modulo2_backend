package com.mf.mf.projection.MFAnulacion;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

public interface GetMFSolicitudAnulacionProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getId();
    String getNombre();
    String getRazonSocial();
    String getIdentificacion();
    String getEmail();
    String getEmail1();
    String getDescripcion();
    String getPeticion();
    String getItemsModificados();
    String getEstadoSolicitud();
    Boolean getEstado();
    LocalDate getFechaSolicitud();
    String getObservacion();

    //REUQERIMIENTO
    Integer getIdProgramacion();
    Integer getActoAdministrativo();
    String getTipoRequerimientoDescripcion();
    String getEstadoRequerimientoDescripcion();
    LocalDate getFechaInicio();
    LocalDate getFechaFin();
    LocalDate getFechaPublicacion();
    Integer getAnnioVigencia();


    //HEREDADOS
    Integer getIdHeredado();
    Integer getNit();
    Integer getEstadoEntrega();
    boolean getIndividual();
    LocalDate getFechaEntrega();

}
