package com.mf.mf.dto.excel;

import lombok.Data;

@Data
public class MFAnexosDTO {
    private long idAnexo;
    private Integer idHeredado;
    private byte[] caratula;
    private byte[] estadoSituacionFinanciera;
    private byte[] estadoResultados;
    private byte[] estadoResultadosIntegral;
    private byte[] flujoEfectivoIndirecto;
    private byte[] flujoEfectivoDirecto;
    private byte[] estadoCambiosPatrimonio;
    private byte[] dictamenFiscal;
    private boolean estado;
}