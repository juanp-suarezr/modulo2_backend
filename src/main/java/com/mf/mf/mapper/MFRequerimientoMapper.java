package com.mf.mf.mapper;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFRequerimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MFRequerimientoMapper {

    // Convertir MFRequerimientoDTO a entidad MFRequerimiento sin delegaturas
    @Mapping(target = "delegatura", ignore = true) // Ignorar delegatura, no está en MFRequerimiento
    MFRequerimiento toEntity(MFRequerimientoDTO dto);

    // Convertir MFHashDelegaturaDTO a entidad MFHashDelegatura
    @Mapping(target = "idRequerimiento.idRequerimiento", source = "idRequerimiento")
    MFHashDelegatura toEntity(MFRequerimientoDTO.MFHashDelegaturaDTO dto);

    // Mapear lista de delegaturas
    List<MFHashDelegatura> toDelegaturaEntities(List<MFRequerimientoDTO.MFHashDelegaturaDTO> dtoList);

    // Convertir MFRequerimiento a MFRequerimientoDTO (agregar delegado manualmente si es necesario)
    @Mapping(target = "delegatura", ignore = true) // Ignorar aquí también para evitar errores
    MFRequerimientoDTO toDto(MFRequerimiento requerimiento);
}
