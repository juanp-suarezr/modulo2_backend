package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFCausalesDisolucion\"")
public class MFCausalesDisolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCausales;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;

    @Column(length = 200, nullable = false)
    private String descripcion;
    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;

}
