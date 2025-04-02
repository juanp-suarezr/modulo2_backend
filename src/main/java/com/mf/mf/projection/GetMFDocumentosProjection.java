package com.mf.mf.projection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public interface GetMFDocumentosProjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long getIdDocumentos();
    Integer getIdHeredado();
    String getNit();
    String getLink();
    Boolean getEstado();


}
