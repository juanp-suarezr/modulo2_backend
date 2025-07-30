package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFActasReunion;
import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFFacultades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFFacultadesRepository extends JpaRepository<MFFacultades, Long> {

    @Query("""
    SELECT DISTINCT fd
    FROM MFFacultades fd
    WHERE fd.constitucion.idSocietario = :idConstitucion
""")
    List<MFFacultades> facultadesByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
