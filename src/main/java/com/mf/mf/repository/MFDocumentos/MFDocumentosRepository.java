package com.mf.mf.repository.MFDocumentos;

import com.mf.mf.model.MFDocumentos;
import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.projection.GetMFDocumentosProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MFDocumentosRepository extends JpaRepository<MFDocumentos, Long> {

    @Query("SELECT d FROM MFDocumentos d WHERE d.idHeredado = :idHeredado "+ "AND d.estado = true")
    Optional<MFDocumentos> findByIdHeredado(@Param("idHeredado") Integer idHeredado);

    //documentos By idHeredados
    @Query("SELECT d " +
            "FROM MFDocumentos d " +
            "WHERE d.idHeredado = :idHeredado " +
            "AND d.estado = true")
    List<GetMFDocumentosProjection> findByIdProjection(@Param("idHeredado") Integer idHeredado);

    //documentos By nit
    @Query("SELECT d " +
            "FROM MFDocumentos d " +
            "WHERE d.nit = :nit " +
            "AND d.estado = true")
    List<GetMFDocumentosProjection> findByNITProjection(@Param("nit") Integer nit);

    //Anexos By idHeredados Model anexos list
    @Query("SELECT a " +
            "FROM MFDocumentos a " +
            "WHERE a.idHeredado = :idHeredado " +
            "AND a.estado = true")
    List<MFDocumentos> findById(@Param("idHeredado") Integer idHeredado);

    //documentos By idHeredados historial
    @Query("SELECT d " +
            "FROM MFDocumentos d " +
            "WHERE d.idHeredado = :idHeredado")
    List<GetMFDocumentosProjection> findByHeredado1(@Param("idHeredado") Integer idHeredado);


    //CAMBIAR A FALSE
    @Modifying
    @Transactional
    @Query("UPDATE MFDocumentos a SET a.estado = false WHERE a.idHeredado = :idHeredado")
    void changeEstado(@Param("idHeredado") Integer idHeredado);


    //ELIMINAR POR IDHeredado
    @Modifying
    @Transactional
    @Query("DELETE FROM MFDocumentos d WHERE d.idHeredado = :idHeredado")
    void deleteByIdHeredado(@Param("idHeredado") Integer idHeredado);

}

