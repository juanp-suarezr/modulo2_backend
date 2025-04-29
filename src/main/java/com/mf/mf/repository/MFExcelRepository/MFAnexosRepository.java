package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosEntregasProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFAnexosRepository extends JpaRepository<MFAnexos, Long> {

    //Anexos By idHeredados
    @Query("SELECT a " +
            "FROM MFAnexos a " +
            "WHERE a.idHeredado = :idHeredado " +
            "AND a.estado = true")
    List<GetMFAnexosProjection> findAnexosByHeredado(@Param("idHeredado") Integer idHeredado);

    //Anexos By idHeredados
    @Query("SELECT a " +
            "FROM MFAnexos a " +
            "WHERE a.idHeredado = :idHeredado")
    List<GetMFAnexosProjection> findAnexosByHeredadoComparativo(@Param("idHeredado") Integer idHeredado);

    //Anexos By idHeredados Model anexos list
    @Query("SELECT a " +
            "FROM MFAnexos a " +
            "WHERE a.idHeredado = :idHeredado " +
            "AND a.estado = true")
    List<MFAnexos> findAnexosByHeredadoList(@Param("idHeredado") Integer idHeredado);

    //CAMBIAR A FALSE
    @Modifying
    @Transactional
    @Query("UPDATE MFAnexos a SET a.estado = false WHERE a.idHeredado = :idHeredado")
    void changeEstado(@Param("idHeredado") Integer idHeredado);


}

