package com.mf.mf.dto.MFReporteSocietario;

import com.mf.mf.model.MFReporteSocietario.MFOrganismosAdministracion;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class MFRegistroSocietarioDTO {
    private String tipo_sociedad;
    private String tipo_documento;
    private String ciudadNotariaId;
    private String notaria;
    private LocalDate fechaDocumento;
    private Integer cantidadAccionistas;
    private BigDecimal capitalAutorizado;
    private BigDecimal capitalPagado;
    private BigDecimal capitalSuscrito;
    private String ciudadCamaraComercio;
    private String distribucionUtilidades;
    private String duracionSociedad;
    private LocalDate fechaConvocatoria;
    private LocalDate fechaPagoCapitalSuscrito;
    private LocalDate fechaRegistroMercantil;
    private LocalDate fechaVigencia;
    private String formaConvocatoria;
    private String nombreCamaraComercio;
    private Integer nroAcciones;
    private Integer nroCuotas;
    private String organismosDecision;
    private String registroMercantil;
    private String tipoAccion;
    private BigDecimal valorAccion;
    private BigDecimal valorCapital;
    private boolean vigenciaIndefinida;
    private Integer idHeredado;
    private Integer nit;

    private List<MFCausalesDisolucionDTO> causales;
    private List<MFFacultadesDTO> facultades;
    private List<MFRevisoresFiscalesDTO> revisoresFiscales;
    private List<MFOrganismosAdministracionDTO> organismosAdministracion;

    //reuniones
    private List<MFReunionesDTO> reuniones;
    //Actas y convocantes
    private List<MFActasDTO> actas;
    private List<MFConvocantesDTO> convocantes;
}

