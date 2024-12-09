package com.mf.mf.dto.excel;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MFIdentificacionVigiladoDTO {

    private Long id;
    private Boolean estado;
    private Number nitSinDigitoVerificacion;
    private Number digitoVerificacion;
    private String nombreSociedad;
    private String grupoNiifReporte;
    private String tipoEstadosFinancieros;
    private String tipoVinculacionEconomica;
    private String tipoSubordinada;
    private String vinculadosEconomicos;
    private String nombreVinculadoEconomico1;
    private String nitVinculadoEconomico1;
    private String nombreVinculadoEconomico2;
    private String nitVinculadoEconomico2;
    private String nombreVinculadoEconomico3;
    private String nitVinculadoEconomico3;
    private String nombreVinculadoEconomico4;
    private String nitVinculadoEconomico4;
    private String nombreVinculadoEconomico5;
    private String nitVinculadoEconomico5;
    private LocalDate fechaInicialEstadosFinancieros;
    private LocalDate fechaCorteEstadosFinancieros;
    private String monedaPresentacion;
    private LocalDate fechaReporte;
    private String periodicidadPresentacion;
    private Integer anoActualReporte;
    private Integer anoComparativo;

}

