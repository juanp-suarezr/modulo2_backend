package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFRequerimientoRepository extends JpaRepository<MFRequerimiento, Long> {

    @Query("SELECT a.idRequerimiento as idRequerimiento, " +
            "a.nombreRequerimiento as nombreRequerimiento, " +
            "a.fechaInicio as fechaInicio, " +
            "a.fechaFin as fechaFin, " +
            "a.fechaCreacion as fechaCreacion, " +
            "a.periodoEntrega as periodoEntrega, " +
            "a.tipoProgramacion as tipoProgramacion, " +
            "a.actoAdministrativo as actoAdministrativo, " +
            "a.fechaPublicacion as fechaPublicacion, " +
            "a.annioVigencia as annioVigencia, " +
            "a.documentoActo as documentoActo, " +
            "a.estadoVigilado as estadoVigilado, " +
            "a.estado as estado, " +
            "f.descripcion as tipoRequerimientoDescripcion " +
            "FROM MFRequerimiento a " +
            "JOIN a.tipoRequerimientoDescripcion f")
    List<GetMFRequerimientoProjection> findAllProjections();

    @Query("SELECT a.idRequerimiento as idRequerimiento, " +
            "a.nombreRequerimiento as nombreRequerimiento, " +
            "a.fechaInicio as fechaInicio, " +
            "a.fechaFin as fechaFin, " +
            "a.fechaCreacion as fechaCreacion, " +
            "a.periodoEntrega as periodoEntrega, " +
            "a.tipoProgramacion as tipoProgramacion, " +
            "a.actoAdministrativo as actoAdministrativo, " +
            "a.fechaPublicacion as fechaPublicacion, " +
            "a.annioVigencia as annioVigencia, " +
            "a.documentoActo as documentoActo, " +
            "a.estadoVigilado as estadoVigilado, " +
            "a.estado as estado, " +
            "f.descripcion as tipoRequerimientoDescripcion " +
            "FROM MFRequerimiento a " +
            "JOIN a.tipoRequerimientoDescripcion f " +
            "WHERE a.idRequerimiento = :idRequerimiento")
    List<GetMFRequerimientoProjection> findProjectionsByIdRequerimiento(Long idRequerimiento);


}
