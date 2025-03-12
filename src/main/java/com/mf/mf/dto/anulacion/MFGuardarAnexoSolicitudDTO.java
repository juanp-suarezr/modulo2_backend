package com.mf.mf.dto.anulacion;

import lombok.Data;

import java.util.List;

@Data
public class MFGuardarAnexoSolicitudDTO {

    private MFSolicitudAnulacionDTO solicitud;
    private List<MFAnexoAnulacionDTO> anexo;
}
