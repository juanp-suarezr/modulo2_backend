package com.mf.mf.projection.MFRequerimientoProjection;

import com.mf.mf.dto.MFCatalogoDetalleDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFHashDelegaturaProjection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdProgramacion();
    Integer getIdDelegatura();
    Integer getIdTipoVigilado();
    LocalDate getFechaFin();
    long getIdRequerimiento();
    Integer getEstadoRequerimiento();
    Boolean getEstado();


//    String getDelegaturaDescripcion();
//    String getEstadoRequerimientos();
//    String getTipoVigilado();



}
