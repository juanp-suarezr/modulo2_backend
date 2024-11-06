package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.mapper.MFHashDelegaturaMapper;
import com.mf.mf.mapper.MFRequerimientoMapper;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDelegaturaRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFRequerimientoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MFRequerimientoServices {

    @Autowired
    private MFRequerimientoRepository mfRequerimientoRepository;
    @Autowired
    private MFHashDelegaturaRepository mfHashDelegaturaRepository; // Añadir el repositorio de MFHashDelegatura
    @Autowired
    private MFRequerimientoMapper mfRequerimientoMapper;
    @Autowired
    private MFHashDelegaturaMapper mfHashDelegaturaMapper; // Añadir el mapper de MFHashDelegatura

    @Transactional
    public MFRequerimientoDTO save(MFRequerimientoDTO mfRequerimientoDTO) {
        // Convertir y guardar MFRequerimiento
        MFRequerimiento requerimiento = mfRequerimientoMapper.toEntity(mfRequerimientoDTO);
        requerimiento = mfRequerimientoRepository.save(requerimiento);

        // Convertir y guardar las delegaturas
        List<MFHashDelegatura> delegaturas = mfRequerimientoMapper.toDelegaturaEntities(mfRequerimientoDTO.getDelegatura());
        for (MFHashDelegatura delegatura : delegaturas) {
            delegatura.setRequerimiento(requerimiento); // Establecer la relación
        }
        mfHashDelegaturaRepository.saveAll(delegaturas);

        // Convertir y devolver el requerimiento a DTO, incluyendo delegaturas manualmente
        MFRequerimientoDTO resultDto = mfRequerimientoMapper.toDto(requerimiento);
        resultDto.setDelegatura(mfRequerimientoDTO.getDelegatura());
        return resultDto;


    }

    public List<GetMFRequerimientoProjection> obtenerRequerimientos() {
        return mfRequerimientoRepository.findAllProjections();
    }

    public void deleteRequerimiento(Long idRequerimiento) {
        mfRequerimientoRepository.deleteById(idRequerimiento);
    }

    public GetMFRequerimientoProjection buscarRequerimientoId(Long idRequerimiento) {
        List<GetMFRequerimientoProjection> results = mfRequerimientoRepository.findProjectionsByIdRequerimiento(idRequerimiento);
        if (results.isEmpty()) {
            throw new EntityNotFoundException("No se encontró la solicitud con el ID: " + idRequerimiento);
        }
        return results.get(0);
    }

    public MFRequerimientoDTO updateRequerimiento(Long idRequerimiento, MFRequerimientoDTO dto) {
        // Buscar la entidad existente por ID
        MFRequerimiento existingEntity = mfRequerimientoRepository.findById(idRequerimiento)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la solicitud con el ID: " + idRequerimiento));
        // Actualizar la entidad con los valores del DTO
        mfRequerimientoMapper.updateEntityFromDto(dto, existingEntity);
        // Guardar la entidad actualizada en la base de datos
        MFRequerimiento updatedEntity = mfRequerimientoRepository.save(existingEntity);
        // Mapear la entidad actualizada de vuelta al DTO
        return mfRequerimientoMapper.toDTO(updatedEntity);
    }

}
