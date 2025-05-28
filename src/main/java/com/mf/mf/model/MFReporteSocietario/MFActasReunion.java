package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFActasReunion\"")
public class MFActasReunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReunion", referencedColumnName = "idReuniones")
    private MFReuniones reunion;

    private Integer numeroActa;

    private LocalDate fechaActa;

    private LocalDate fechaInscripcion;

    private Integer nroReunion;

    @Column(length = 100)
    private String temasTratados;

    private Boolean reformaEstatutaria;

    @Column(nullable = false)
    private Integer nit;

    @Column(nullable = false)
    private Boolean estado = true;
}
