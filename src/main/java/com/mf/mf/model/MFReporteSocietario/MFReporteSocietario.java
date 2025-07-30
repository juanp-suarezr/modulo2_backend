package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFReporteSocietario\"")
public class MFReporteSocietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSocietario;
    private String tipoSociedad;

    @Column(nullable = false)
    private String tipoDocumentoCreacion;
    private String ciudadNotariaId;
    private String notaria;
    private LocalDate fechaDocumento;
    @Column(length = 10, nullable = false)
    private String registroMercantil;
    @Column(nullable = false)
    private LocalDate fechaRegistroMercantil;
    private String ciudadCamaraComercio;
    private String nombreCamaraComercio;
    private Boolean vigenciaIndefinida = false;
    private LocalDate fechaVigencia;
    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFCausalesDisolucion> causalesDisolucion;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFDatosAdicionales> datosAdicionales;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFFacultades> facultades;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFDatosCapital> datosCapital;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFRevisoresFiscales> revisoresFiscales;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFOrganismosAdministracion> organismosAdministracion;

    @OneToMany(mappedBy = "constitucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MFReuniones> reuniones;


}
