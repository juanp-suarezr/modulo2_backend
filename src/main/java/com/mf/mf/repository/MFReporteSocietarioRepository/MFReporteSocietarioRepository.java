package com.mf.mf.repository.MFReporteSocietarioRepository;

import com.mf.mf.model.MFDocumentos;
import com.mf.mf.model.MFReporteSocietario.MFReporteSocietario;
import com.mf.mf.projection.GetMFDocumentosProjection;
import com.mf.mf.projection.MFReporteSocietario.GetMFReporteSocietarioProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFReporteSocietarioRepository extends JpaRepository<MFReporteSocietario, Long> {

    List<GetMFReporteSocietarioProjection> findAllProjectedBy();

    @Query("""
    SELECT rs
    FROM MFReporteSocietario rs
    LEFT JOIN FETCH rs.causalesDisolucion cd
    LEFT JOIN FETCH rs.datosAdicionales da
    LEFT JOIN FETCH rs.facultades f
    LEFT JOIN FETCH rs.datosCapital dc
    LEFT JOIN FETCH rs.revisoresFiscales rf
    LEFT JOIN FETCH rs.organismosAdministracion oa
    LEFT JOIN FETCH rs.reuniones r
    WHERE rs.nit = :nit AND rs.idHeredado = :idHeredado
""")
    Optional<MFReporteSocietario> findWithRelationsByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);

}

