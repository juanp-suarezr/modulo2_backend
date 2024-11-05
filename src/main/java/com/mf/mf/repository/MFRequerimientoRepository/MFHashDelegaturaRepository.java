package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDelegaturaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFHashDelegaturaRepository extends JpaRepository<MFHashDelegatura, Long> {


    @Query("SELECT p.idProgramacion as idProgramacion, " +
            "p.idDelegatura as idDelegatura, " +
            "p.idTipoVigilado as idTipoVigilado, " +
            "p.idRequerimiento as idRequerimiento, " +
            "p.fechaFin as fechaFin, " +
            "p.estado as estado, " +
            "p.estadoRequerimiento as estadoRequerimiento " +
            "FROM MFHashDelegatura p " +
            "WHERE p.idRequerimiento = :idRequerimiento")
    List<GetMFHashDelegaturaProjection> findProjectionsByIdRequerimiento(Long idRequerimiento);


}