package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFFacultades\"")
public class MFFacultades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacultades;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;

    private String rol;
    private String descripcion;
    private Boolean estado = true;
    @Column(nullable = false)
    private Integer idHeredado;
    @Column(nullable = false)
    private Integer nit;

}
