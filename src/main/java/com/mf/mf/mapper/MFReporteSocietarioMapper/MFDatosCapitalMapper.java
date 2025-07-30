package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFCausalesDisolucionDTO;
import com.mf.mf.dto.MFReporteSocietario.MFDatosCapitalDTO;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFDatosCapitalMapper {
    MFDatosCapitalDTO toDTO(MFDatosCapital entity);
    MFDatosCapital toEntity(MFDatosCapitalDTO dto);
}
