package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFOrganismosAdministracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFOrganismosAdministracionRepository extends JpaRepository<MFOrganismosAdministracion, Long> {

    @Query("""
    SELECT DISTINCT oa
    FROM MFOrganismosAdministracion oa
    WHERE oa.constitucion.idSocietario = :idConstitucion
""")
    List<MFOrganismosAdministracion> organismosAdminByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
