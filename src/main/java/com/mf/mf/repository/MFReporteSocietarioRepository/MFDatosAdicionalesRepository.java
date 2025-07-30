package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFDatosAdicionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFDatosAdicionalesRepository extends JpaRepository<MFDatosAdicionales, Long> {

    @Query("""
    SELECT DISTINCT da
    FROM MFDatosAdicionales da
    WHERE da.constitucion.idSocietario = :idConstitucion
""")
    List<MFDatosAdicionales> datosAdicionalesByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
