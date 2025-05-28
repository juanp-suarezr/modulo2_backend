package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFConvocantesReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFAcasReunionRepository extends JpaRepository<MFActasReunion, Long> {
}
