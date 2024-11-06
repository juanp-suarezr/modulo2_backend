package com.mf.mf.mapper;

import com.mf.mf.dto.MFHashDelegaturaDTOO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.model.MFHashDelegatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MFHashDelegaturaMapper {

    MFRequerimientoDTO.MFHashDelegaturaDTO toDTO(MFHashDelegatura hashDelegatura);

    MFHashDelegatura toEntity(MFHashDelegaturaDTOO MFHashDelegaturaDTO);

    // Método para actualizar una entidad existente desde un DTO
    @Mapping(target = "idProgramacion", ignore = true)
    // Ignora el ID porque no queremos sobreescribirlo
    void updateEntityFromDto(MFRequerimientoDTO.MFHashDelegaturaDTO MFHashDelegaturaDTO, @MappingTarget MFHashDelegatura hashDelegatura);

}
