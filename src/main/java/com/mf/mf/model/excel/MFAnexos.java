package com.mf.mf.model.excel;

import com.mf.mf.model.CatalogoDetalle;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFHashDigitoNIT;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(schema = "financiero", name = "MFAnexos")
public class MFAnexos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnexo;
    private Integer idHeredado; // fk
    private String caratula;
    private String estadoSituacionFinanciera;
    private String estadoResultados;
    private String estadoResultadosIntegral;
    private String flujoEfectivo;
    private String estadoCambiosPatrimonio;
    private String dictamenFiscal;
    private String revelacionesEstadosFinancieros;
    private String notasEstadosFinancieros;
    private String certificacionCumplimientoEEFF;
    private String politicasContables;
    private String informeGestion;
    private String proyectoDistribucionUtilidadesEmpresas;
    private String declaracionRenta;
    private String composicionAccionaria;
    private String actaAsambleaAprobacionEF;
    private LocalDate fechaEntrega;
    private boolean estado;




}
