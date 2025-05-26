package com.mf.mf.model.MFReporteSocietario;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFDatosCapital\"")
public class MFDatosCapital {
    @Id
    private Long idDatosCapital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConstitucion", referencedColumnName = "idSocietario")
    private MFReporteSocietario constitucion;

    @Column(name = "valor_capital", nullable = false)
    private BigDecimal valorCapital;

    @Column(name = "capital_autorizado", nullable = false)
    private BigDecimal capitalAutorizado;

    @Column(name = "nro_acciones", nullable = false)
    private Integer nroAcciones;

    @Column(name = "capital_suscrito", nullable = false)
    private BigDecimal capitalSuscrito;

    @Column(name = "fecha_pago_capital_suscrito")
    private LocalDate fechaPagoCapitalSuscrito;

    @Column(name = "nro_cuotas", nullable = false)
    private Integer nroCuotas;

    @Column(name = "tipo_accion", nullable = false, length = 100)
    private String tipoAccion;

    @Column(name = "valor_accion", nullable = false)
    private BigDecimal valorAccion;

    @Column(name = "capital_pagado", nullable = false)
    private BigDecimal capitalPagado;

    @Column(name = "cantidad_accionistas", nullable = false)
    private Integer cantidadAccionistas;

    @Column(name = "idHeredado", nullable = false)
    private Integer idHeredado;

    @Column(name = "nit", nullable = false)
    private Integer nit;

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

}
