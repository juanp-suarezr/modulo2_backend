package com.mf.mf.mapper;

import com.mf.mf.dto.MFHashDelegaturaDTO;
import com.mf.mf.model.MFHashDelegatura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MFHashDelegaturaMapper {

    MFHashDelegaturaDTO toDTO(MFHashDelegatura hashDelegatura);

    MFHashDelegatura toEntity(MFHashDelegaturaDTO MFHashDelegaturaDTO);

    // Método para actualizar una entidad existente desde un DTO
    @Mapping(target = "idProgramacion", ignore = true)
    // Ignora el ID porque no queremos sobreescribirlo
    void updateEntityFromDto(MFHashDelegaturaDTO MFHashDelegaturaDTO, @MappingTarget MFHashDelegatura hashDelegatura);

}
