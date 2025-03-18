package com.mf.mf.model.anulacion;

import com.mf.mf.model.CatalogoDetalle;
import com.mf.mf.model.MFHashHeredado;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFSolicitudAnulacion\"")
public class MFSolicitudAnulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer idHeredado; // FK<
    private String nombre;
    private String razonSocial;
    private String identificacion;
    private String email;
    private String email1;
    private String descripcion;
    private String peticion;
    private String itemsModificados;
    private String estadoSolicitud;
    private String observacion;
    private boolean estado;
    private LocalDate fechaSolicitud; // asignacion por debajo

    // Relaciones con otras entidades

    @ManyToOne
    @JoinColumn(name = "\"idHeredado\"", referencedColumnName = "idHeredado", insertable = false, updatable = false)
    private MFHashHeredado heredado;

}
