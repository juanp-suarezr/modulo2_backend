package com.mf.mf.mapper.MFReporteSocietarioMapper;

import com.mf.mf.dto.MFReporteSocietario.MFReporteSocietarioWithRelationshipDTO;
import com.mf.mf.model.MFReporteSocietario.MFReporteSocietario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        MFDatosCapitalMapper.class,
        MFDatosAdicionalesMapper.class,
        MFCausalesDisolucionMapper.class,
        MFFacultadesMapper.class,
        MFRevisoresFiscalesMapper.class,
        MFOrganismosAdministracionMapper.class,
        MFReunionesMapper.class,
        // agregar otros mappers relacionados
})
public interface MFReporteSocietarioMapper {

    MFReporteSocietarioWithRelationshipDTO toDTO(MFReporteSocietario entidad);

    MFReporteSocietario toEntity(MFReporteSocietarioWithRelationshipDTO dto);

}
