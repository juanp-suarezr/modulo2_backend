package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoResultadoIntegralORIProjection {

    Long getId();

    Boolean getEstado();

    Integer getGananciaPerdidaNeta();

    Integer getOtroResultadoIntegralDiferenciasCambioConversion();

    Integer getOtroResultadoIntegralGananciasActuariales();

    Integer getOtroResultadoIntegralGananciasRevaluacion();

    Integer getTotalOtroResultadoIntegralNoReclasificable();

    Integer getGananciasCoberturasFlujosEfectivo();

    Integer getTotalOtroResultadoIntegralReclasificable();

    Integer getParticipacionOtroResultadoIntegralSubsidiarias();

    Integer getTotalOtroResultadoIntegral();

    Integer getResultadoIntegralTotal();

    Integer getResultadoIntegralPropietariosControladora();

    Integer getResultadoIntegralParticipacionesNoControladoras();

    String getValidacionEstadoResultados();

    Integer getNit();
}


