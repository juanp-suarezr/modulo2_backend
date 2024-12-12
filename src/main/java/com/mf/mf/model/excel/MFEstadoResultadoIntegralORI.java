package com.mf.mf.model.excel;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFEstadoResultadoIntegralORI\"")
public class MFEstadoResultadoIntegralORI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;

    private Integer gananciaPerdidaNeta;
    private Integer otroResultadoIntegralDiferenciasCambioConversion;
    private Integer otroResultadoIntegralGananciasActuariales;
    private Integer otroResultadoIntegralGananciasRevaluacion;
    private Integer totalOtroResultadoIntegralNoReclasificable;
    private Integer gananciasCoberturasFlujosEfectivo;
    private Integer totalOtroResultadoIntegralReclasificable;

    private Integer participacionOtroResultadoIntegralSubsidiarias;

    private Integer totalOtroResultadoIntegral;

    private Integer resultadoIntegralTotal;

    private Integer resultadoIntegralPropietariosControladora;

    private Integer resultadoIntegralParticipacionesNoControladoras;


    private String validacionEstadoResultados;
    private Integer nit;
    private Integer annio;
//    private Boolean actual;


}

