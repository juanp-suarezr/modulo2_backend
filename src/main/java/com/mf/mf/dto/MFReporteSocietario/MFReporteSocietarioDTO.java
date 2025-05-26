package com.mf.mf.dto.MFReporteSocietario;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MFReporteSocietarioDTO {
    private long idSocietario;
    private String tipoSociedad;
    private String tipoDocumentoCreacion;
    private Integer ciudadNotariaId;
    private String notaria;
    private LocalDate fechaDocumento;
    private String registroMercantil;
    private LocalDate fechaRegistroMercantil;
    private String ciudadCamaraComercio;
    private String nombreCamaraComercio;
    private Boolean vigenciaIndefinida;
    private LocalDate fechaVigencia;
    private Boolean estado;
    private Integer idHeredado;
    private Integer nit;


}