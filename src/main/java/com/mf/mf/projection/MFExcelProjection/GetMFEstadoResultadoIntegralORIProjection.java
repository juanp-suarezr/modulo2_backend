package com.mf.mf.projection.MFExcelProjection;

public interface GetMFEstadoResultadoIntegralORIProjection {

    Long getId();

    Boolean getEstado();

    Long getGananciaPerdidaNeta();

    Long getOtroResultadoIntegralDiferenciasCambioConversion();

    Long getOtroResultadoIntegralGananciasActuariales();

    Long getOtroResultadoIntegralGananciasRevaluacion();

    Long getTotalOtroResultadoIntegralNoReclasificable();

    Long getGananciasCoberturasFlujosEfectivo();

    Long getTotalOtroResultadoIntegralReclasificable();

    Long getParticipacionOtroResultadoIntegralSubsidiarias();

    Long getTotalOtroResultadoIntegral();

    Long getResultadoIntegralTotal();

    Long getResultadoIntegralPropietariosControladora();

    Long getResultadoIntegralParticipacionesNoControladoras();

    String getValidacionEstadoResultados();

    Integer getNit();
    Integer getIdHeredado();
    Integer getAnnio();
    Boolean getActual();
}


