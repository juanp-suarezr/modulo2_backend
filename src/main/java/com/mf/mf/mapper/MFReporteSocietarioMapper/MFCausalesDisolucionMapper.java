package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFCausalesDisolucionDTO;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFCausalesDisolucionMapper {
    MFCausalesDisolucionDTO toDTO(MFCausalesDisolucion entity);
    MFCausalesDisolucion toEntity(MFCausalesDisolucionDTO dto);
}
