package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import com.mf.mf.model.MFReporteSocietario.MFReuniones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFReunionesRepository extends JpaRepository<MFReuniones, Long> {
}
