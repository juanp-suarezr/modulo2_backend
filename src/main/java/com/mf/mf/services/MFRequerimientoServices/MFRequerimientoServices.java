package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFHashDelegaturaDTO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.mapper.MFHashDelegaturaMapper;
import com.mf.mf.mapper.MFRequerimientoMapper;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFHashDigitoNIT;
import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDelegaturaRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDigitoNITRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFRequerimientoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MFRequerimientoServices {

    @Autowired
    private MFRequerimientoRepository mfRequerimientoRepository;
    @Autowired
    private MFHashDelegaturaRepository mfHashDelegaturaRepository; // Añadir el repositorio de MFHashDelegatura

    @Autowired
    private MFHashDigitoNITRepository mfHashDigitoNITRepository; // Añadir el repositorio de MFHashDelegatura
    @Autowired
    private MFRequerimientoMapper mfRequerimientoMapper;

    //guardar con programaciones
    @Transactional
    public MFRequerimientoDTO save(MFRequerimientoDTO mfRequerimientoDTO) {
        // Convertir y guardar la entidad de MFRequerimiento
        MFRequerimiento entity = mfRequerimientoMapper.toEntity(mfRequerimientoDTO);
        MFRequerimiento savedEntity = mfRequerimientoRepository.save(entity);

        try {
            // Verificar el tipoProgramacion y, si coincide, crear el registro en MFHashDelegatura
            if (mfRequerimientoDTO.getTipoProgramacion().equals(232)) { // reemplaza "especificoId" por el ID específico
                List<MFHashDelegatura> delegaturaEntities = mfRequerimientoMapper.toEntity(mfRequerimientoDTO.getDelegaturas());
                for (MFHashDelegatura delegatura : delegaturaEntities) {
                    delegatura.setIdRequerimiento(savedEntity.getIdRequerimiento());
                    mfHashDelegaturaRepository.save(delegatura);
                }
            } else if(mfRequerimientoDTO.getTipoProgramacion().equals(234)) {
                List<MFHashDigitoNIT> digitoNITEntities = mfRequerimientoMapper.toDigitoNITEntity(mfRequerimientoDTO.getDigitoNIT());
                for (MFHashDigitoNIT digitoNIT : digitoNITEntities) {
                    digitoNIT.setIdRequerimiento(savedEntity.getIdRequerimiento());
                    mfHashDigitoNITRepository.save(digitoNIT);
                }
            }
        } catch (Exception e) {
            mfRequerimientoRepository.delete(savedEntity);
            // Si ocurre un error al guardar MFHashDelegatura, lanzar una excepción
            throw new RuntimeException("Error al crear la delegatura hash. Eliminando el requerimiento...", e);
        }

        // Retornar el DTO de MFRequerimiento
        return mfRequerimientoMapper.toDTO(savedEntity);
    }

    //Obtener detalles completos
    public GetMFRequerimientoProjection obtenerRequerimientoByID(Long idRequerimiento) {
        List<GetMFRequerimientoProjection> requerimientos = mfRequerimientoRepository.findProjectionsByIdRequerimiento(idRequerimiento);

        try {
            return requerimientos.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Error: No se encontró el requerimiento con ID " + idRequerimiento, e);
        }
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
