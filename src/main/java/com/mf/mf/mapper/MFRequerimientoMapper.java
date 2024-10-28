package com.mf.mf.mapper;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.model.MFRequerimiento;
import org.springframework.web.bind.annotation.Mapping;

public interface MFRequerimientoMapper {

    MFRequerimientoDTO toDTO(MFRequerimiento requerimiento);
    MFRequerimiento toEntity(MFRequerimientoDTO MFRequerimientoDTO);

    // Método para actualizar una entidad existente desde un DTO
    @Mapping(target = "idRequerimiento", ignore = true) // Ignora el ID porque no queremos sobreescribirlo
    void updateEntityFromDto(MFRequerimientoDTO MFRequerimientoDTO, @MappingTarget MFRequerimiento requerimiento);

}
