package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFDatosAdicionalesRepository extends JpaRepository<MFDatosAdicionales, Long> {
}
