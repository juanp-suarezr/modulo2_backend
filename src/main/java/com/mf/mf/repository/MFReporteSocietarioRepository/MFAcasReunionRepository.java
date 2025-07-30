package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFConvocantesReunion;
import com.mf.mf.model.MFReporteSocietario.MFReporteSocietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFAcasReunionRepository extends JpaRepository<MFActasReunion, Long> {

    @Query("""
    SELECT DISTINCT ar
    FROM MFActasReunion ar
    WHERE ar.reunion.idReuniones = :idReunion
""")
    List<MFActasReunion> actasReunionesByIdHeredado(@Param("idReunion") Integer idReunion);


}
