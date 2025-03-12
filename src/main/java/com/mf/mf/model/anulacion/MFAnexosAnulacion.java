package com.mf.mf.model.anulacion;

import com.mf.mf.model.MFHashHeredado;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFAnexosAnulacion\"")
public class MFAnexosAnulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer idAnulacion; // FK
    private String nombre;
    private String detalle;
    private String path;
    private boolean estado;


    // Relaciones con otras entidades

    @ManyToOne
    @JoinColumn(name = "\"idAnulacion\"", referencedColumnName = "id", insertable = false, updatable = false)
    private MFSolicitudAnulacion anulacion;

}
