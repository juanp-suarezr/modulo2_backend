package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFIdentificacionVigilado;
import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFIdentificacionVigiladoRepository extends JpaRepository<MFIdentificacionVigilado, Long> {

    @Query("SELECT t FROM MFIdentificacionVigilado t " +
            "WHERE t.nit = :nit "+
            "AND t.estado = true "+
            "AND t.idHeredado = :idHeredado")
    List<GetMFIdentificacionVigiladoProjection> findMFIdentificacionVigiladosByNit(Integer nit, Integer idHeredado);

}
