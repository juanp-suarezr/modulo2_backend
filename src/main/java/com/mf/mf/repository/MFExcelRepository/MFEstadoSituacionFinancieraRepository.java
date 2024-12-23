package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoSituacionFinanciera;
import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoSituacionFinancieraProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoSituacionFinancieraRepository extends JpaRepository<MFEstadoSituacionFinanciera, Long> {

    @Query("SELECT t FROM MFEstadoSituacionFinanciera t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoSituacionFinancieraProjection> findMFESFByNit(Integer nit, Integer idHeredado);


}
