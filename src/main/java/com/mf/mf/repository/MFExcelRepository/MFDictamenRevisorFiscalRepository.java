package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFDictamenRevisorFiscal;
import com.mf.mf.model.excel.MFEstadoFlujoEfectivoDirecto;
import com.mf.mf.projection.MFExcelProjection.GetMFDictamenRevisorFiscalProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoFlujoDirectoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFDictamenRevisorFiscalRepository extends JpaRepository<MFDictamenRevisorFiscal, Long> {

    @Query("SELECT t FROM MFDictamenRevisorFiscal t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFDictamenRevisorFiscalProjection> findMFDictamenByNit(Integer nit, Integer idHeredado);

    //DELETE
    @Modifying
    @Query("DELETE FROM MFDictamenRevisorFiscal t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);


}
