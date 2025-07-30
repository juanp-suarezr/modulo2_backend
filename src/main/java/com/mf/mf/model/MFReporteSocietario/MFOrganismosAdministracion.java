package com.mf.mf.model.MFReporteSocietario;

import com.mf.mf.model.CatalogoDetalle;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFOrganismosAdministracion\"")
public class MFOrganismosAdministracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrganismos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;


    private String tipo_identificacion;
    private String nro_identificacion;
    private String nombres;
    private String apellidos;
    private String tipo;
    private LocalDate fecha_inscripcion;
    private Boolean activo;
    private String nro_tarjeta_profesional;
    private String calidad;

    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;


}
