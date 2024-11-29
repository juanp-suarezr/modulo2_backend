package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFRequerimientoWithHashDTO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.mapper.MFRequerimientoMapper;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFHashDigitoNIT;
import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.*;
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
        // Eliminar relaciones hijas temporalmente

        entity.setDelegaturas(null);
        entity.setDigitoNIT(null);

        // Guardar el requerimiento sin relaciones
        MFRequerimiento savedEntity = mfRequerimientoRepository.save(entity);
        mfRequerimientoRepository.flush();
        System.out.println("entity save: " + mfRequerimientoMapper.toDTO(savedEntity));

        try {
            // Verificar el tipoProgramacion y, si coincide, crear el registro en MFHashDelegatura
            if (mfRequerimientoDTO.getTipoProgramacion().equals(232)) {
                List<MFHashDelegatura> delegaturaEntities = mfRequerimientoMapper.toEntity(mfRequerimientoDTO.getDelegaturas());
                delegaturaEntities.forEach(delegatura -> delegatura.setIdRequerimiento(savedEntity.getIdRequerimiento()));
                System.out.println("Delegatura entities: " + delegaturaEntities);

                mfHashDelegaturaRepository.saveAll(delegaturaEntities);
            } else if (mfRequerimientoDTO.getTipoProgramacion().equals(234)) {
                List<MFHashDigitoNIT> digitoNITEntities = mfRequerimientoMapper.toDigitoNITEntity(mfRequerimientoDTO.getDigitoNIT());
                digitoNITEntities.forEach(digitoNIT -> digitoNIT.setIdRequerimiento(savedEntity.getIdRequerimiento()));
                mfHashDigitoNITRepository.saveAll(digitoNITEntities);
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
    public MFRequerimientoWithHashDTO obtenerRequerimientoByID(Long idRequerimiento) {
        // 1. Obtener el requerimiento usando la proyección GetMFRequerimientoProjection
        List<GetMFRequerimientoProjection> requerimientos = mfRequerimientoRepository.findProjectionsByIdRequerimiento(idRequerimiento);

        try {
            // Obtener el primer requerimiento de la lista
            GetMFRequerimientoProjection requerimiento = requerimientos.get(0);

            // 2. Obtener hash separados
            List<GetMFHashDigitoNITProjection> digitoNIT = mfHashDigitoNITRepository.findProjectionsByIdRequerimiento(idRequerimiento);
            List<GetMFHashDelegaturaProjection> delegaturas = mfHashDelegaturaRepository.findProjectionsByIdRequerimiento(idRequerimiento);

            // 3. Crear el DTO con los datos
            MFRequerimientoWithHashDTO result = new MFRequerimientoWithHashDTO(requerimiento, digitoNIT, delegaturas);

            // 4. Devolver el DTO
            return result;
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Error: No se encontró el requerimiento con ID " + idRequerimiento, e);
        }
    }



    //Obtener requerimientos para tabla principal
    public List<GetMFRequerimientosTableProjection> obtenerRequerimientos() {
        return mfRequerimientoRepository.findAllProjections();
    }

    //Anular requerimiento
    @Transactional
    public MFRequerimientoDTO AnularRequerimiento(Long idRequerimiento) {
        // Buscar la entidad principal
        MFRequerimiento requerimiento = mfRequerimientoRepository.findById(idRequerimiento)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el requerimiento con ID: " + idRequerimiento));

        // Actualizar el estado del requerimiento principal
        requerimiento.setEstadoRequerimiento(291);
        MFRequerimiento updatedRequerimiento = mfRequerimientoRepository.save(requerimiento);

        // Actualizar el estado en las entidades relacionadas (delegaturas y dígitos NIT)
        if (updatedRequerimiento.getTipoProgramacion().equals(232)) {
            List<MFHashDelegatura> delegaturas = mfHashDelegaturaRepository.findByIdRequerimiento(idRequerimiento);
            delegaturas.forEach(delegatura -> delegatura.setEstadoRequerimiento(291));
            mfHashDelegaturaRepository.saveAll(delegaturas);
        } else if (updatedRequerimiento.getTipoProgramacion().equals(234)) {
            List<MFHashDigitoNIT> digitosNIT = mfHashDigitoNITRepository.findByIdRequerimiento(idRequerimiento);
            digitosNIT.forEach(digitoNIT -> digitoNIT.setEstadoRequerimiento(291));
            mfHashDigitoNITRepository.saveAll(digitosNIT);
        }

        // Retornar el DTO actualizado
        return mfRequerimientoMapper.toDTO(updatedRequerimiento);
    }






}
