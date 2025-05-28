package com.mf.mf.model.MFReporteSocietario;

import com.mf.mf.model.CatalogoDetalle;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFRevisoresFiscales\"")
public class MFRevisoresFiscales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRevisoresFiscales;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;

    private String tipo_identificacion;
    private String nro_identificacion;
    private String nombre;
    private Boolean principal;
    private Boolean activo;
    private LocalDate fecha_inscripcion;
    private String nro_tarjeta_profesional;
    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;


    // Relaciones con otras entidades

    @ManyToOne
    @JoinColumn(name = "\"tipo_identificacion\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle tipoDocumentoDescripcion;

}
