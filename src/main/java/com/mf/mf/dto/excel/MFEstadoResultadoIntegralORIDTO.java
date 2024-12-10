package com.mf.mf.dto.excel;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class MFEstadoResultadoIntegralORIDTO {

    private Long id;
    private Boolean estado;
    private Integer gananciaPerdidaNeta; // Ganancia/Pérdida neta
    private Integer otroResultadoIntegralDiferenciasCambioConversion; // Diferencias de cambio por conversión
    private Integer otroResultadoIntegralGananciasActuariales; // Ganancias actuariales
    private Integer otroResultadoIntegralGananciasRevaluacion; // Ganancias por revaluación
    private Integer totalOtroResultadoIntegralNoReclasificable; // Total no reclasificable
    private Integer gananciasCoberturasFlujosEfectivo; // Ganancias por coberturas de flujos de efectivo
    private Integer totalOtroResultadoIntegralReclasificable; // Total reclasificable

    private Integer participacionOtroResultadoIntegralSubsidiarias; // Participación de subsidiarias

    private Integer totalOtroResultadoIntegral; // Total otro resultado integral

    private Integer resultadoIntegralTotal; // Resultado integral total

    private Integer resultadoIntegralPropietariosControladora; // Propietarios de la controladora
    private Integer resultadoIntegralParticipacionesNoControladoras; // Participaciones no controladoras

    private String validacionEstadoResultados; // Validación del estado de resultados
    private Integer nit; // NIT del vigilado


}

