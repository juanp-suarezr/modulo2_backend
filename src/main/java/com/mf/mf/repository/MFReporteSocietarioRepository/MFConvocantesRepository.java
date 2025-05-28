package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFConvocantesReunion;
import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFConvocantesRepository extends JpaRepository<MFConvocantesReunion, Long> {
}
