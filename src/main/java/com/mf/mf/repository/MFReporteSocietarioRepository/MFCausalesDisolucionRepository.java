package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFReporteSocietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFCausalesDisolucionRepository extends JpaRepository<MFCausalesDisolucion, Long> {

    @Query("""
    SELECT DISTINCT cd
    FROM MFCausalesDisolucion cd
    WHERE cd.constitucion.idSocietario = :idConstitucion
""")
    List<MFCausalesDisolucion> causalesDisolucionByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
