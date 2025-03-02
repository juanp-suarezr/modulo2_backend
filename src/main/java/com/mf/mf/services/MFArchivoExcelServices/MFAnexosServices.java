package com.mf.mf.services.MFArchivoExcelServices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.repository.MFExcelRepository.MFAnexosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class MFAnexosServices {

    private final MFAnexosRepository mfAnexosRepository;


}
