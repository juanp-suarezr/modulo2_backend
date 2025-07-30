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
    private Long saldoInicialPeriodoActual;
    private Long disminucionIncrementoDistribucionesPropietarios;
    private Long incrementoDisminucionTransferenciasOtrosCambios;
    private Long incrementoDisminucionTransaccionesAccionesCartera;
    private Long incrementoDisminucionCambiosParticipacionSubsidiarias;
    private Long totalCambiosPatrimonio;
    private Long saldoFinalPeriodoActual;
    private Long incrementoDisminucionPoliticasContables;
    private Long incrementoDisminucionCorreccionesErrores;
    private Long saldoInicialReexpresado;
    private Long cambiosEnPatrimonio;
    private Long resultadoIntegral;
    private Long gananciaPerdida;
    private Long otroResultadoIntegral;
    private Long resultadoIntegralFinal;
    private Long emisionPatrimonio;
    private Long dividendos;
    private Long incrementoDisminucionAportacionesPropietarios;
    private Integer annio;
    private Integer nit;
    private Boolean actual;
    private Integer idHeredado;

    
}
