package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFDatosAdicionalesDTO;
import com.mf.mf.dto.MFReporteSocietario.MFDatosCapitalDTO;
import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFDatosAdicionalesMapper {
    MFDatosAdicionalesDTO toDTO(MFDatosAdicionales entity);
    MFDatosAdicionales toEntity(MFDatosAdicionalesDTO dto);
}
