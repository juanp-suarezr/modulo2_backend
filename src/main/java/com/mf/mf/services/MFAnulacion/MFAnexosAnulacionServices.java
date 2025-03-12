package com.mf.mf.services.MFAnulacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.repository.MFAnulacion.MFAnexoAnulacionRepository;
import com.mf.mf.repository.MFAnulacion.MFSolicitudAnulacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class MFAnexosAnulacionServices {

    private final MFAnexoAnulacionRepository mfAnexoAnulacionRepository;


}
