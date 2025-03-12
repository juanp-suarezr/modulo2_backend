package com.mf.mf.services.MFAnulacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.repository.MFAnulacion.MFSolicitudAnulacionRepository;
import com.mf.mf.repository.MFExcelRepository.MFAnexosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class MFSolicitudAnulacionServices {

    private final MFSolicitudAnulacionRepository mfSolicitudAnulacionRepository;


}
