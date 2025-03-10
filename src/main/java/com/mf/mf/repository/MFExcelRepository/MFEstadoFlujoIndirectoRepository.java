package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoFlujoEfectivoIndirecto;
import com.mf.mf.model.excel.MFEstadoResultados;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoFlujoIndirectoProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoResultadosProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoFlujoIndirectoRepository extends JpaRepository<MFEstadoFlujoEfectivoIndirecto, Long> {

    @Query("SELECT t FROM MFEstadoFlujoEfectivoIndirecto t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoFlujoIndirectoProjection> findMFEFEIndirectoByNit(Integer nit, Integer idHeredado);

    //DELETE
    @Modifying
    @Query("DELETE FROM MFEstadoFlujoEfectivoIndirecto t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);

}
