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
