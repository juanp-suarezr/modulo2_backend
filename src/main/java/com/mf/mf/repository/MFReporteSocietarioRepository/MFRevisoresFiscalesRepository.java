package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFRevisoresFiscales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFRevisoresFiscalesRepository extends JpaRepository<MFRevisoresFiscales, Long> {
}
