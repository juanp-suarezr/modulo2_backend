package com.mf.mf.repository.MFAnulacion;

import com.mf.mf.model.anulacion.MFAnexosAnulacion;
import com.mf.mf.model.anulacion.MFSolicitudAnulacion;
import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.projection.MFAnulacion.GetMFAnexoAnulacionProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFAnexoAnulacionRepository extends JpaRepository<MFAnexosAnulacion, Long>  {

    //Anexos anulacion By idAnulados
    @Query("SELECT a " +
            "FROM MFAnexosAnulacion a " +
            "WHERE a.idAnulacion = :idAnulacion")
    List<GetMFAnexoAnulacionProjection> findAnexosByAnulado(@Param("idAnulacion") Integer idAnulacion);




}
