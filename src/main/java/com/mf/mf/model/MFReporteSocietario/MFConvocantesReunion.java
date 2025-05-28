package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFconvocantesReunion\"")
public class MFConvocantesReunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvocante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReunion", referencedColumnName = "idReuniones")
    private MFReuniones reunion;

    private String tipoDocumento;

    @Column(length = 10)
    private String nroIdentificacion;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 100)
    private String rol;

    @Column(nullable = false)
    private Integer nit;

    @Column(nullable = false)
    private Boolean estado = true;
}


