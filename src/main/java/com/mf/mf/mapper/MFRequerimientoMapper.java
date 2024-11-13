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

    MFRequerimientoDTO toDTO(MFRequerimiento requerimiento);

    MFRequerimiento toEntity(MFRequerimientoDTO MFRequerimientoDTO);

    // Método para actualizar la entidad MFRequerimiento desde el DTO, ignorando ciertos campos
    @Mapping(target = "idRequerimiento", ignore = true) // Ignora el ID
    void updateEntityFromDto(MFRequerimientoDTO MFRequerimientoDTO, @MappingTarget MFRequerimiento requerimiento);

    //Conexion con DTO HASH DELEGATURAS
    List<MFHashDelegaturaDTO> toDTO(List<MFHashDelegatura> delegaturas);
    List<MFHashDelegatura> toEntity(List<MFHashDelegaturaDTO> delegaturasDTO);

    //CONEXION DTO DIGITO-NIT
    List<MFHashDigitoNITDTO> toDigitoNITDTO(List<MFHashDigitoNIT> digitoNIT);
    List<MFHashDigitoNIT> toDigitoNITEntity(List<MFHashDigitoNITDTO> digitoNITDTO);

}
