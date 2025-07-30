package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFConvocantesReunion;
import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFConvocantesRepository extends JpaRepository<MFConvocantesReunion, Long> {

    @Query("""
    SELECT DISTINCT cd
    FROM MFConvocantesReunion cd
    WHERE cd.reunion.idReuniones = :idReunion
""")
    List<MFConvocantesReunion> convocantesByIdHeredado(@Param("idReunion") Integer idReunion);


}
