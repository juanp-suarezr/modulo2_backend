package com.mf.mf.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MFVigiladoDTO {

    private Integer idVigilado;
    private Integer nit;

    public MFVigiladoDTO(Integer idVigilado, Integer nit) {
        this.idVigilado = idVigilado;
        this.nit = nit;
    }

    public Integer getIdVigilado() {
        return idVigilado;
    }

    public void setIdVigilado(Integer idVigilado) {
        this.idVigilado = idVigilado;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

}
