package com.mf.mf.dto.anulacion;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFSolicitudAnulacionDTO {

    private long id;
    private Integer idHeredado; // FK
    private String nombre;
    private String razonSocial;
    private String identificacion;
    private String email;
    private String email1;
    private String descripcion;
    private String peticion;
    private String itemsModificados;
    private String estadoSolicitud;
    private boolean estado;
    private LocalDate fechaSolicitud;



}
