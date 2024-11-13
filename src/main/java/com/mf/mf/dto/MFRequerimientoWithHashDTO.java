package com.mf.mf.dto;

import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDelegaturaProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDigitoNITProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import java.util.List;

@Data
public class MFRequerimientoWithHashDTO {

    private long idRequerimiento;
    private Integer nombreRequerimiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaCreacion;
    private Integer periodoEntrega;
    private Integer tipoProgramacion;
    private Integer actoAdministrativo;
    private LocalDate fechaPublicacion;
    private Integer annioVigencia;
    private byte[] documentoActo;
    private Integer estadoVigilado;
    private Integer estadoRequerimiento;
    private Boolean estado;
    private String tipoRequerimientoDescripcion;
    private String periodoEntregaDescripcion;
    private String tipoProgramacionDescripcion;
    private String estadoVigiladoDescripcion;
    private String estadoRequerimientoDescripcion;

    // Lista de digitos NIT
    private List<GetMFHashDigitoNITProjection> digitoNIT;
    // Lista de delegaturas
    private List<GetMFHashDelegaturaProjection> delegaturas;

    // Constructor
    public MFRequerimientoWithHashDTO(GetMFRequerimientoProjection requerimiento,
                              List<GetMFHashDigitoNITProjection> digitoNIT, List<GetMFHashDelegaturaProjection> delegaturas) {
        this.idRequerimiento = requerimiento.getIdRequerimiento();
        this.nombreRequerimiento = requerimiento.getNombreRequerimiento();
        this.fechaInicio = requerimiento.getFechaInicio();
        this.fechaFin = requerimiento.getFechaFin();
        this.fechaCreacion = requerimiento.getFechaCreacion();
        this.periodoEntrega = requerimiento.getPeriodoEntrega();
        this.tipoProgramacion = requerimiento.getTipoProgramacion();
        this.actoAdministrativo = requerimiento.getActoAdministrativo();
        this.fechaPublicacion = requerimiento.getFechaPublicacion();
        this.annioVigencia = requerimiento.getAnnioVigencia();
        this.documentoActo = requerimiento.getDocumentoActo();
        this.estadoVigilado = requerimiento.getEstadoVigilado();
        this.estadoRequerimiento = requerimiento.getEstadoRequerimiento();
        this.estado = requerimiento.getEstado();
        this.tipoRequerimientoDescripcion = requerimiento.getTipoRequerimientoDescripcion();
        this.periodoEntregaDescripcion = requerimiento.getPeriodoEntregaDescripcion();
        this.tipoProgramacionDescripcion = requerimiento.getTipoProgramacionDescripcion();
        this.estadoVigiladoDescripcion = requerimiento.getEstadoVigiladoDescripcion();
        this.estadoRequerimientoDescripcion = requerimiento.getEstadoRequerimientoDescripcion();
        //datos hash
        this.digitoNIT = digitoNIT;
        this.delegaturas = delegaturas;
    }

    // Getters y setters
    public long getIdRequerimiento() {
        return idRequerimiento;
    }

    public Integer getNombreRequerimiento() {
        return nombreRequerimiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Integer getPeriodoEntrega() {
        return periodoEntrega;
    }

    public Integer getTipoProgramacion() {
        return tipoProgramacion;
    }

    public Integer getActoAdministrativo() {
        return actoAdministrativo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Integer getAnnioVigencia() {
        return annioVigencia;
    }

    public byte[] getDocumentoActo() {
        return documentoActo;
    }

    public Integer getEstadoVigilado() {
        return estadoVigilado;
    }

    public Integer getEstadoRequerimiento() {
        return estadoRequerimiento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getTipoRequerimientoDescripcion() {
        return tipoRequerimientoDescripcion;
    }

    public String getPeriodoEntregaDescripcion() {
        return periodoEntregaDescripcion;
    }

    public String getTipoProgramacionDescripcion() {
        return tipoProgramacionDescripcion;
    }

    public String getEstadoVigiladoDescripcion() {
        return estadoVigiladoDescripcion;
    }

    public String getEstadoRequerimientoDescripcion() {
        return estadoRequerimientoDescripcion;
    }

    public List<GetMFHashDigitoNITProjection> getDigitoNIT() {
        return digitoNIT;
    }
    public List<GetMFHashDelegaturaProjection> getDelegaturas() {
        return delegaturas;
    }



    public void setDigitoNIT(List<GetMFHashDigitoNITProjection> digitoNIT) {
        this.digitoNIT = digitoNIT;
    }
    public void setDelegaturas(List<GetMFHashDelegaturaProjection> delegaturas) {
        this.delegaturas = delegaturas;
    }
}
