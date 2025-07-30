package com.mf.mf.dto.MFReporteSocietario;

import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MFReporteSocietarioWithRelationshipDTO {
    private long idSocietario;
    private String tipoSociedad;
    private String tipoDocumentoCreacion;
    private String ciudadNotariaId;
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

    private List<MFCausalesDisolucionDTO> causalesDisolucion;
    private List<MFDatosCapitalDTO> datosCapital;
    private List<MFDatosAdicionalesDTO> datosAdicionales;
    private List<MFFacultadesDTO> facultades;
    private List<MFRevisoresFiscalesDTO> revisoresFiscales;
    private List<MFOrganismosAdministracionDTO> organismosAdministracion;

    //reuniones
    private List<MFReunionesDTO> reuniones;


}