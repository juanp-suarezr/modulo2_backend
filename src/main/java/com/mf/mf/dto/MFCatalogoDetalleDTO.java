package com.mf.mf.dto;

public class MFCatalogoDetalleDTO {

    private String descripcion;

    // Constructor
    public MFCatalogoDetalleDTO(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

