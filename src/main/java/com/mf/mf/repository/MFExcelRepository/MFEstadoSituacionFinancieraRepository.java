package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoSituacionFinanciera;
import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoSituacionFinancieraProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFEstadoSituacionFinancieraRepository extends JpaRepository<MFEstadoSituacionFinanciera, Long> {

    @Query("SELECT t FROM MFEstadoSituacionFinanciera t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFEstadoSituacionFinancieraProjection> findMFESFByNit(Integer nit, Integer idHeredado);

    //DELETE
    @Modifying
    @Query("DELETE FROM MFEstadoSituacionFinanciera t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);

    //CAMBIAR A FALSE
    @Modifying
    @Transactional
    @Query("UPDATE MFEstadoSituacionFinanciera e SET e.estado = false WHERE e.idHeredado = :idHeredado")
    void updateEstadoByIdHeredado(@Param("idHeredado") Integer idHeredado);

}
