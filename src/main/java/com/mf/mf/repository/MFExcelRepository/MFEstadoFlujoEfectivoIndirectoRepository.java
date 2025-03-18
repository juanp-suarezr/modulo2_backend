package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoFlujoEfectivoIndirecto;
import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoFlujoDirectoProjection;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MFEstadoFlujoEfectivoIndirectoRepository extends JpaRepository<MFEstadoFlujoEfectivoIndirecto, Long> {

    @Query("SELECT t FROM MFEstadoFlujoEfectivoIndirecto t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoFlujoDirectoProjection> findMFEFEIndirectoByNit(Integer nit, Integer idHeredado);

    //DELETE
    @Modifying
    @Query("DELETE FROM MFEstadoFlujoEfectivoIndirecto t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);

    //CAMBIAR A FALSE
    @Modifying
    @Transactional
    @Query("UPDATE MFEstadoFlujoEfectivoIndirecto e SET e.estado = false WHERE e.idHeredado = :idHeredado")
    void updateEstadoByIdHeredado(@Param("idHeredado") Integer idHeredado);

}
