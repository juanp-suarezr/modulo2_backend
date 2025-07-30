package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFCausalesDisolucionDTO;
import com.mf.mf.dto.MFReporteSocietario.MFOrganismosAdministracionDTO;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFOrganismosAdministracion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MFOrganismosAdministracionMapper {
    MFOrganismosAdministracionDTO toDTO(MFOrganismosAdministracion entity);
    MFOrganismosAdministracion toEntity(MFOrganismosAdministracionDTO dto);
}
