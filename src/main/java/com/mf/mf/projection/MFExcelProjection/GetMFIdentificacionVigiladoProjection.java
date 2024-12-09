package com.mf.mf.projection.MFExcelProjection;

import java.time.LocalDate;

public interface GetMFIdentificacionVigiladoProjection {

    Long getId();
    Boolean getEstado();
    Number getNitSinDigitoVerificacion();
    Number getDigitoVerificacion();
    String getNombreSociedad();
    String getGrupoNiifReporte();
    String getTipoEstadosFinancieros();
    String getTipoVinculacionEconomica();
    String getTipoSubordinada();
    String getVinculadosEconomicos();
    String getNombreVinculadoEconomico1();
    String getNitVinculadoEconomico1();
    String getNombreVinculadoEconomico2();
    String getNitVinculadoEconomico2();
    String getNombreVinculadoEconomico3();
    String getNitVinculadoEconomico3();
    String getNombreVinculadoEconomico4();
    String getNitVinculadoEconomico4();
    String getNombreVinculadoEconomico5();
    String getNitVinculadoEconomico5();
    LocalDate getFechaInicialEstadosFinancieros();
    LocalDate getFechaCorteEstadosFinancieros();
    String getMonedaPresentacion();
    LocalDate getFechaReporte();
    String getPeriodicidadPresentacion();
    Integer getAnoActualReporte();
    Integer getAnoComparativo();

}


