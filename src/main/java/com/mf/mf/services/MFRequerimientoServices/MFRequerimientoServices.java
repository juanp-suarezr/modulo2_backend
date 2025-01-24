package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.*;
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
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private MFHeredadosServices mfHeredadosServices;

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


            // Verificar el tipoProgramacion y, si coincide, crear el registro en MFHashDelegatura
            if (mfRequerimientoDTO.getTipoProgramacion().equals(232)) {


                // Crear una lista temporal para retener los valores de "vigilados"
                List<MFHashDelegaturaDTO> delegaturaDTOs = mfRequerimientoDTO.getDelegaturas();
                List<List<MFVigiladoDTO>> vigiladosTemp = delegaturaDTOs.stream()
                        .map(MFHashDelegaturaDTO::getVigilados)
                        .toList();


                List<MFHashDelegatura> delegaturaEntities = mfRequerimientoMapper.toEntity(mfRequerimientoDTO.getDelegaturas());

                delegaturaEntities.forEach(delegatura -> delegatura.setIdRequerimiento(savedEntity.getIdRequerimiento()));

                // Guardar las entidades y recuperar las persistidas
                List<MFHashDelegatura> savedDelegaturas = mfHashDelegaturaRepository.saveAll(delegaturaEntities);

                // Mapear las entidades guardadas nuevamente a DTOs
                List<MFHashDelegaturaDTO> updatedDelegaturaDTOs = mfRequerimientoMapper.toDTO(savedDelegaturas);

                // Restaurar los valores de "vigilados" desde el mapa
                // Reasignar los valores "vigilados" desde la lista temporal
                for (int i = 0; i < updatedDelegaturaDTOs.size(); i++) {
                    updatedDelegaturaDTOs.get(i).setVigilados(vigiladosTemp.get(i));
                }

                // Procesar los valores "vigilados"
                for (MFHashDelegaturaDTO delegaturaDTO : updatedDelegaturaDTOs) {
                    System.out.println(delegaturaDTO);
                    if (delegaturaDTO.getVigilados() != null && !delegaturaDTO.getVigilados().isEmpty()) {
                        mfHeredadosServices.crearRegistros(
                                delegaturaDTO.getIdProgramacion(), // idProgramacion asignado después
                                delegaturaDTO.getVigilados(),      // vigilados restaurado
                                delegaturaDTO.getFechaFin(),
                                delegaturaDTO.getEstadoRequerimiento() == 289 ? 285 : 286,
                                false,
                                mfRequerimientoDTO.getTipoProgramacion()
                        );
                    }
                }


            }
            else if (mfRequerimientoDTO.getTipoProgramacion().equals(234)) {


                // Crear una lista temporal para retener los valores de "vigilados"
                List<MFHashDigitoNITDTO> digitoNitDTOs = mfRequerimientoDTO.getDigitoNIT();
                List<List<MFVigiladoDTO>> vigiladosTemp = digitoNitDTOs.stream()
                        .map(MFHashDigitoNITDTO::getVigilados)
                        .toList();
                System.out.println(vigiladosTemp);
                List<MFHashDigitoNIT> digitoNITEntities = mfRequerimientoMapper.toDigitoNITEntity(mfRequerimientoDTO.getDigitoNIT());
                digitoNITEntities.forEach(digitoNIT -> digitoNIT.setIdRequerimiento(savedEntity.getIdRequerimiento()));
                List<MFHashDigitoNIT> savedDigitoNit = mfHashDigitoNITRepository.saveAll(digitoNITEntities);

                // Mapear las entidades guardadas nuevamente a DTOs
                List<MFHashDigitoNITDTO> updatedDigitoNITDTOs = mfRequerimientoMapper.toDigitoNITDTO(savedDigitoNit);

                // Reasignar los valores "vigilados" desde la lista temporal
                for (int i = 0; i < updatedDigitoNITDTOs.size(); i++) {
                    updatedDigitoNITDTOs.get(i).setVigilados(vigiladosTemp.get(i));
                }

                // Procesar vigilados de cada digitoNIT
                for (MFHashDigitoNITDTO digitoNITDTO : updatedDigitoNITDTOs) {
                    System.out.println(digitoNITDTO);
                    if (digitoNITDTO.getVigilados() != null && !digitoNITDTO.getVigilados().isEmpty()) {
                        mfHeredadosServices.crearRegistros(
                                digitoNITDTO.getIdProgramacion(),
                                digitoNITDTO.getVigilados(),
                                digitoNITDTO.getFechaFin(),
                                digitoNITDTO.getEstadoRequerimiento() == 289 ? 285 : 286,
                                false,
                                mfRequerimientoDTO.getTipoProgramacion()
                        );
                    }
                }

            }
            else {
                mfHeredadosServices.crearRegistros(
                        savedEntity.getIdRequerimiento(),
                        mfRequerimientoDTO.getVigiladoNIT(),
                        mfRequerimientoDTO.getFechaFin(),
                        mfRequerimientoDTO.getEstadoRequerimiento() == 289 ? 285 : 286,
                        true,
                        mfRequerimientoDTO.getTipoProgramacion()
                );
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
