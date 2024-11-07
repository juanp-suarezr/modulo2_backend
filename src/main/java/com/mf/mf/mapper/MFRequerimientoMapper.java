package com.mf.mf.mapper;

import com.mf.mf.dto.MFHashDelegaturaDTO;
import com.mf.mf.dto.MFHashDigitoNITDTO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFHashDigitoNIT;
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

    // Método para actualizar la entidad MFRequerimiento desde el DTO, ignorando ciertos campos
    @Mapping(target = "idRequerimiento", ignore = true) // Ignora el ID
    @Mapping(target = "documentoActo", ignore = true) // Opcional: ignora documentoActo si no deseas sobreescribirlo
    void updateEntityFromDto(MFRequerimientoDTO MFRequerimientoDTO, @MappingTarget MFRequerimiento requerimiento);

    //Conexion con DTO HASH DELEGATURAS
    List<MFHashDelegaturaDTO> toDTO(List<MFHashDelegatura> delegaturas);
    List<MFHashDelegatura> toEntity(List<MFHashDelegaturaDTO> delegaturasDTO);

    //CONEXION DTO DIGITO-NIT
    List<MFHashDigitoNITDTO> toDigitoNITDTO(List<MFHashDigitoNIT> digitoNIT);
    List<MFHashDigitoNIT> toDigitoNITEntity(List<MFHashDigitoNITDTO> digitoNITDTO);

    // Convertir MFRequerimiento a MFRequerimientoDTO (agregar delegado manualmente si es necesario)
    @Mapping(target = "delegatura", ignore = true) // Ignorar aquí también para evitar errores
    MFRequerimientoDTO toDto(MFRequerimiento requerimiento);
}
