package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFDatosCapitalRepository extends JpaRepository<MFDatosCapital, Long> {
}
