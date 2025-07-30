package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFCausalesDisolucionDTO;
import com.mf.mf.dto.MFReporteSocietario.MFFacultadesDTO;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFFacultades;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFFacultadesMapper {
    MFFacultadesDTO toDTO(MFFacultades entity);
    MFFacultades toEntity(MFFacultadesDTO dto);
}
