package com.mf.mf.dto.excel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFEstadoCambioPatrimonioDTO {

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
    private Integer idHeredado;
    private Integer nit;

}

