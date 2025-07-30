package com.mf.mf.dto.MFReporteSocietario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MFDatosCapitalDTO {

    private Integer cantidadAccionistas;
    private BigDecimal capitalAutorizado;
    private BigDecimal capitalPagado;
    private BigDecimal capitalSuscrito;
    private LocalDate fechaPagoCapitalSuscrito;
    private Integer nroAcciones;
    private Integer nroCuotas;
    private String tipoAccion;
    private BigDecimal valorAccion;
    private BigDecimal valorCapital;
    private Integer idHeredado;
    private Integer nit;
}
