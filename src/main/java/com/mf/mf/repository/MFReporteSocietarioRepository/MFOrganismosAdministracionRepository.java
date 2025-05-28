package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFOrganismosAdministracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFOrganismosAdministracionRepository extends JpaRepository<MFOrganismosAdministracion, Long> {
}
