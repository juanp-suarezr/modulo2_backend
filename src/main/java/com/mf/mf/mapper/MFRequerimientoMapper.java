package com.mf.mf.mapper;

import com.mf.mf.dto.MFHashDelegaturaDTO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFRequerimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MFRequerimientoMapper {

    MFRequerimientoDTO toDTO(MFRequerimiento requerimiento);

    MFRequerimiento toEntity(MFRequerimientoDTO MFRequerimientoDTO);

    // Método para actualizar la entidad MFRequerimiento desde el DTO, ignorando ciertos campos
    @Mapping(target = "idRequerimiento", ignore = true) // Ignora el ID
    @Mapping(target = "documentoActo", ignore = true) // Opcional: ignora documentoActo si no deseas sobreescribirlo
    void updateEntityFromDto(MFRequerimientoDTO MFRequerimientoDTO, @MappingTarget MFRequerimiento requerimiento);

    // Método específico para la clase anidada (si es necesario mapear MFHashDelegaturaDTO a una entidad)
    MFHashDelegaturaDTO toDTO(MFHashDelegatura delegatura);
    MFHashDelegatura toEntity(MFHashDelegaturaDTO delegaturaDTO);

}
