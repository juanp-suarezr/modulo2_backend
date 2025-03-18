package com.mf.mf.dto.anulacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstadoSolicitudRequest {

    @NotBlank(message = "El estado de la solicitud no puede estar vacío.")
    private String estadoReq;

    @NotBlank(message = "La observación no puede estar vacía.")
    private String observacion;

    @NotNull(message = "El ID de la solicitud es obligatorio.")
    private Integer id;

    // Getters y Setters
    public String getEstadoReq() {
        return estadoReq;
    }

    public void setEstadoReq(String estadoReq) {
        this.estadoReq = estadoReq;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

