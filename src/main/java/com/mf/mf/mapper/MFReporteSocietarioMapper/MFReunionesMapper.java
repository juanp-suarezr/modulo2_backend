package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFCausalesDisolucionDTO;
import com.mf.mf.dto.MFReporteSocietario.MFReunionesDTO;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFReuniones;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFReunionesMapper {
    MFReunionesDTO toDTO(MFReuniones entity);
    MFReuniones toEntity(MFReunionesDTO dto);
}
