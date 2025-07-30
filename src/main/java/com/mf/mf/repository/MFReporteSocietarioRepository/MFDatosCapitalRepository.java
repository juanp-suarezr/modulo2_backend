package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFDatosCapitalRepository extends JpaRepository<MFDatosCapital, Long> {

    @Query("""
    SELECT DISTINCT dc
    FROM MFDatosCapital dc
    WHERE dc.constitucion.idSocietario = :idConstitucion
""")
    List<MFDatosCapital> datosCapitalByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
