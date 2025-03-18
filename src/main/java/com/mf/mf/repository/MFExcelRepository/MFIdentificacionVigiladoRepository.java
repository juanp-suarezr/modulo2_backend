package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFIdentificacionVigiladoRepository extends JpaRepository<MFIdentificacionVigilado, Long> {

    @Query("SELECT t FROM MFIdentificacionVigilado t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFIdentificacionVigiladoProjection> findMFIdentificacionVigiladosByNit(Integer nit, Integer idHeredado);

    // En MFIdentificacionVigiladoRepository
    @Modifying
    @Query("DELETE FROM MFIdentificacionVigilado t WHERE t.nit = :nit AND t.idHeredado = :idHeredado")
    void deleteByNitAndIdHeredado(@Param("nit") Integer nit, @Param("idHeredado") Integer idHeredado);


    @Modifying
    @Transactional
    @Query("UPDATE MFIdentificacionVigilado e SET e.estado = false WHERE e.idHeredado = :idHeredado")
    void updateEstadoByIdHeredado(@Param("idHeredado") Integer idHeredado);

}
