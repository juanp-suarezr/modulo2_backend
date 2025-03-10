package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoFlujoEfectivoDirecto;
import com.mf.mf.model.excel.MFEstadoFlujoEfectivoIndirecto;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoFlujoDirectoProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoFlujoIndirectoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoFlujoDirectoRepository extends JpaRepository<MFEstadoFlujoEfectivoDirecto, Long> {

    @Query("SELECT t FROM MFEstadoFlujoEfectivoDirecto t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoFlujoDirectoProjection> findMFEFEDirectoByNit(Integer nit, Integer idHeredado);

    //DELETE
    @Modifying
    @Query("DELETE FROM MFEstadoFlujoEfectivoDirecto t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);


}
