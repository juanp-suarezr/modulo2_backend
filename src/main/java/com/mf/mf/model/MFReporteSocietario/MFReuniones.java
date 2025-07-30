package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFReuniones\"")
public class MFReuniones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReuniones;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;

    private Integer nro_reunion;
    private LocalDate fecha_reunion;
    private String tipo_reunion;
    private LocalDate fecha_convocatoria;
    private String direccion;
    private String municipio_id;
    private String medio_informacion;
    private LocalDate fecha_antelacion;
    private Boolean es_asamblea_general;
    private String observaciones;
    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;

    @OneToMany(mappedBy = "reunion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFConvocantesReunion> convocantes;

    @OneToMany(mappedBy = "reunion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFActasReunion> actas;
}
