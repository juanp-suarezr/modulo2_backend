package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoCambioPatrimonio;
import com.mf.mf.projection.MFExcelProjection.GetMFDictamenRevisorFiscalProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoCambioPatrimonioProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoCambioPatrimonioRepository extends JpaRepository<MFEstadoCambioPatrimonio, Long> {

    @Query("SELECT t FROM MFEstadoCambioPatrimonio t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoCambioPatrimonioProjection> findMFestadoPatrimonioByNit(Integer nit, Integer idHeredado);

    //SIN ESTADO PARA HISTORIAL MISIONAL
    @Query("SELECT t FROM MFEstadoCambioPatrimonio t " +
            "WHERE t.nit = :nit "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoCambioPatrimonioProjection> findEstadoPatrimonioByNit1(Integer nit, Integer idHeredado);


    //DELETE
    @Modifying
    @Query("DELETE FROM MFEstadoCambioPatrimonio t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);

    //CAMBIAR A FALSE
    @Modifying
    @Transactional
    @Query("UPDATE MFEstadoCambioPatrimonio e SET e.estado = false WHERE e.idHeredado = :idHeredado")
    void updateEstadoByIdHeredado(@Param("idHeredado") Integer idHeredado);

}
