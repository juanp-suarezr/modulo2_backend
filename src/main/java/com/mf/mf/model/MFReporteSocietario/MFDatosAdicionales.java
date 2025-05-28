package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFDatosAdicionales\"")
public class MFDatosAdicionales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDatosAdicionales;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;


    private String distribucion_utilidades;
    private String duracion_sociedad;
    private LocalDate fecha_convocatoria;
    private String forma_convocatoria;
    private String organos_decision;
    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;

}
