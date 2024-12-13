package com.mf.mf.model.excel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFEstadoCambioPatrimonio\"")
public class MFEstadoCambioPatrimonio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;
    private Integer saldoInicialPeriodoActual;
    private Integer disminucionIncrementoDistribucionesPropietarios;
    private Integer incrementoDisminucionTransferenciasOtrosCambios;
    private Integer incrementoDisminucionTransaccionesAccionesCartera;
    private Integer incrementoDisminucionCambiosParticipacionSubsidiarias;
    private Integer totalCambiosPatrimonio;
    private Integer saldoFinalPeriodoActual;
    private Integer incrementoDisminucionPoliticasContables;
    private Integer incrementoDisminucionCorreccionesErrores;
    private Integer saldoInicialReexpresado;
    private Integer cambiosEnPatrimonio;
    private Integer resultadoIntegral;
    private Integer gananciaPerdida;
    private Integer otroResultadoIntegral;
    private Integer resultadoIntegralFinal;
    private Integer emisionPatrimonio;
    private Integer dividendos;
    private Integer incrementoDisminucionAportacionesPropietarios;
    private Integer annio;
    private Integer nit;
    private Boolean actual;
    
}
