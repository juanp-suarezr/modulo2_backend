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

    private Long gananciaPerdidaNeta;
    private Long otroResultadoIntegralDiferenciasCambioConversion;
    private Long otroResultadoIntegralGananciasActuariales;
    private Long otroResultadoIntegralGananciasRevaluacion;
    private Long totalOtroResultadoIntegralNoReclasificable;
    private Long gananciasCoberturasFlujosEfectivo;
    private Long totalOtroResultadoIntegralReclasificable;

    private Long participacionOtroResultadoIntegralSubsidiarias;

    private Long totalOtroResultadoIntegral;

    private Long resultadoIntegralTotal;

    private Long resultadoIntegralPropietariosControladora;

    private Long resultadoIntegralParticipacionesNoControladoras;


    private String validacionEstadoResultados;
    private Integer nit;
    private Integer annio;
    private Integer idHeredado;
    private Boolean actual;


}

