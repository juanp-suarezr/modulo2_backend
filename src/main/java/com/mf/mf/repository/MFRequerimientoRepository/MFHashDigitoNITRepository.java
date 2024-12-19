package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFHashDigitoNIT;
import com.mf.mf.projection.GetMFHashDigitoNITMUVProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDigitoNITProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFHashDigitoNITRepository extends JpaRepository<MFHashDigitoNIT, Long> {


    @Query("SELECT p.idProgramacion as idProgramacion, " +
            "p.idNumeroDigitos as idNumeroDigitos, " +
            "p.inicioRango as inicioRango, " +
            "p.finRango as finRango, " +
            "p.idRequerimiento as idRequerimiento, " +
            "p.fechaFin as fechaFin, " +
            "p.estado as estado, " +
            "p.estadoRequerimiento as estadoRequerimiento " +
            "FROM MFHashDigitoNIT p " +
            "WHERE p.idRequerimiento = :idRequerimiento")
    List<GetMFHashDigitoNITProjection> findProjectionsByIdRequerimiento(Long idRequerimiento);

    List<MFHashDigitoNIT> findByIdRequerimiento(Long idRequerimiento);

    //PARA DIGITO UNICO
    @Query("SELECT p.idProgramacion as idProgramacion, " +
            "p.idNumeroDigitos as idNumeroDigitos, " +
            "p.inicioRango as inicioRango, " +
            "p.finRango as finRango, " +
            "p.fechaFin as fechaFin, " +
            "p.estadoRequerimiento as estadoRequerimiento, " +
            "p.estado as estado " +
             "FROM MFHashDigitoNIT p " +
            "WHERE p.estadoRequerimiento = 289 "+
            "AND p.idNumeroDigitos = 255 "+
            "AND p.inicioRango = :nit "+
            "AND p.estado = true")
    List<GetMFHashDigitoNITMUVProjection> findByNITunico(String nit);

    //CONSULTA PARA DIGITO INICIAL Y FINAL
    @Query("SELECT p.idProgramacion as idProgramacion, " +
            "p.idNumeroDigitos as idNumeroDigitos, " +
            "p.inicioRango as inicioRango, " +
            "p.idRequerimiento as idRequerimiento, " +
            "p.finRango as finRango, " +
            "p.fechaFin as fechaFin, " +
            "p.estado as estado, " +
            "p.estadoRequerimiento as estadoRequerimiento " +
            "FROM MFHashDigitoNIT p " +
            "WHERE p.idNumeroDigitos = :NumeroDigitos "+
            "AND :nit BETWEEN p.inicioRango AND p.finRango")
    List<GetMFHashDigitoNITMUVProjection> findByNITultimosDIgitos(@Param("NumeroDigitos") Integer idNumeroDigitos, String nit);

    //CONSULTA PARA DIGITO INICIAL Y FINAL 3 DIGITOS
    @Query("SELECT p.idProgramacion as idProgramacion, " +
            "p.idNumeroDigitos as idNumeroDigitos, " +
            "p.inicioRango as inicioRango, " +
            "p.finRango as finRango, " +
            "p.fechaFin as fechaFin, " +
            "p.estado as estado, " +
            "p.estadoRequerimiento as estadoRequerimiento " +
            "FROM MFHashDigitoNIT p " +
            "WHERE p.idNumeroDigitos = :NumeroDigitos "+
            "AND :nit BETWEEN p.inicioRango AND p.finRango")
    List<GetMFHashDigitoNITMUVProjection> findByNITultimos3DIgitos(@Param("NumeroDigitos") Integer idNumeroDigitos, String nit);

}
