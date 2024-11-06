package com.mf.mf.projection.MFRequerimientoProjection;

import com.mf.mf.model.CatalogoDetalle;
import jakarta.persistence.*;

import java.time.LocalDate;

public interface GetMFRequerimientoProjection {

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

    byte[] getDocumentoActo();

    Integer getEstadoVigilado();

    Integer getEstadoRequerimiento();

    Boolean getEstado();

    String getTipoRequerimientoDescripcion();

    String getPeriodoEntregaDescripcion();

    String getTipoProgramacionDescripcion();

    String getEstadoVigiladoDescripcion();

    String getEstadoRequerimientoDescripcion();
}
