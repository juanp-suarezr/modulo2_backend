package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFReporteSocietario.MFCausalesDisolucion;
import com.mf.mf.model.MFReporteSocietario.MFDatosCapital;
import com.mf.mf.model.MFReporteSocietario.MFReuniones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFReunionesRepository extends JpaRepository<MFReuniones, Long> {

    @Query("""
    SELECT DISTINCT cd
    FROM MFReuniones cd
    WHERE cd.constitucion.idSocietario = :idConstitucion
""")
    List<MFReuniones> reunionesByIdHeredado(@Param("idConstitucion") Integer idConstitucion);


}
