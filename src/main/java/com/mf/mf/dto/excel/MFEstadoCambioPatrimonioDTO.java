package com.mf.mf.dto.excel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFEstadoCambioPatrimonioDTO {

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

}

