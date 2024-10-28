package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.mapper.MFRequerimientoMapper;
import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.repository.MFRequerimientoRepository.MFRequerimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MFRequerimientoServices {

    @Autowired
    private MFRequerimientoRepository mfRequerimientoRepository;
    @Autowired
    private MFRequerimientoMapper mfRequerimientoMapper;

    public MFRequerimientoDTO save(MFRequerimientoDTO mfRequerimientoDTO) {
        MFRequerimiento entity = mfRequerimientoMapper.toEntity(mfRequerimientoDTO);
        MFRequerimiento savedEntity = mfRequerimientoRepository.save(entity);
        return mfRequerimientoMapper.toDTO(savedEntity);
    }

}
