package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFCausalesDisolucionDTO;
import com.mf.mf.dto.MFReporteSocietario.MFRevisoresFiscalesDTO;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFRevisoresFiscales;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFRevisoresFiscalesMapper {
    MFRevisoresFiscalesDTO toDTO(MFRevisoresFiscales entity);
    MFRevisoresFiscales toEntity(MFRevisoresFiscalesDTO dto);
}
