package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoResultadoIntegralORI;
import com.mf.mf.model.excel.MFEstadoSituacionFinanciera;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoResultadoIntegralORIProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoResultadosProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoResultadoIntegralORIRepository extends JpaRepository<MFEstadoResultadoIntegralORI, Long> {

    @Query("SELECT t FROM MFEstadoResultadoIntegralORI t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoResultadoIntegralORIProjection> findMFORIByNit(Integer nit, Integer idHeredado);

}
