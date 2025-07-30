package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFRevisoresFiscales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFRevisoresFiscalesRepository extends JpaRepository<MFRevisoresFiscales, Long> {

    @Query("""
    SELECT DISTINCT rf
    FROM MFRevisoresFiscales rf
    WHERE rf.constitucion.idSocietario = :idConstitucion
""")
    List<MFRevisoresFiscales> revisoresFiscalesByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
