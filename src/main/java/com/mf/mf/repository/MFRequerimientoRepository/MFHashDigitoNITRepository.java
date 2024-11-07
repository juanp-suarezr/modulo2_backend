package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFHashDigitoNIT;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDigitoNITProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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


}
