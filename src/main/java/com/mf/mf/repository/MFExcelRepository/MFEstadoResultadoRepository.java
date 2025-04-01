package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoResultadoIntegralORI;
import com.mf.mf.model.excel.MFEstadoResultados;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoResultadosProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoSituacionFinancieraProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoResultadoRepository extends JpaRepository<MFEstadoResultados, Long> {

    @Query("SELECT t FROM MFEstadoResultados t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoResultadosProjection> findMFERByNit(Integer nit, Integer idHeredado);

    //SIN ESTADO PARA HISTORIAL MISIONAL
    @Query("SELECT t FROM MFEstadoResultados t " +
            "WHERE t.nit = :nit "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoResultadosProjection> findMFERByNit1(Integer nit, Integer idHeredado);


    //DELETE
    @Modifying
    @Query("DELETE FROM MFEstadoResultados t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);

    //CAMBIAR A FALSE
    @Modifying
    @Transactional
    @Query("UPDATE MFEstadoResultados e SET e.estado = false WHERE e.idHeredado = :idHeredado")
    void updateEstadoByIdHeredado(@Param("idHeredado") Integer idHeredado);

}
