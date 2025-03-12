package com.mf.mf.projection.MFAnulacion;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

public interface GetMFSolicitudAnulacionProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getId();
    String getNombre();
    String getApellido();
    String getIdentificacion();
    String getEmail();
    String getEmail1();
    String getDescripcion();
    String getPeticion();
    String getItemsModificados();
    String getEstadoSolicitud();
    Boolean getEstado();
    LocalDate getFechaSolicitud();

}
