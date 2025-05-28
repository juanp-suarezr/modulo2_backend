package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFReporteSocietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFCausalesDisolucionRepository extends JpaRepository<MFCausalesDisolucion, Long> {
}
