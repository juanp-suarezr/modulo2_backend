package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosTableProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFRequerimientoRepository extends JpaRepository<MFRequerimiento, Long> {

    @Query("SELECT a.idRequerimiento as idRequerimiento, " +
            "a.nombreRequerimiento as nombreRequerimiento, " +
            "a.actoAdministrativo as actoAdministrativo, " +
            "a.annioVigencia as annioVigencia, " +
            "a.fechaInicio as fechaInicio, " +
            "a.fechaFin as fechaFin, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "r.descripcion as estadoRequerimientoDescripcion " + // Agregado
            "FROM MFRequerimiento a " +
            "JOIN a.tipoRequerimientoDescripcion f " +
            "JOIN a.estadoRequerimientoDescripcion r")

    List<GetMFRequerimientosTableProjection> findAllProjections();

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
            "a.estadoVigilado as estadoVigilado, " +
            "a.estadoRequerimiento as estadoRequerimiento, " +
            "a.estado as estado, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "p.descripcion as periodoEntregaDescripcion, " +
            "t.descripcion as tipoProgramacionDescripcion, " + // Agregado
            "e.descripcion as estadoVigiladoDescripcion, " + // Agregado
            "r.descripcion as estadoRequerimientoDescripcion, " + // Agregado
            "d as delegaturas, " + //hash delegatura
            "n as digitoNIT " + //hash digitoNIT
            "FROM MFRequerimiento a " +
            "JOIN a.tipoRequerimientoDescripcion f " +
            "JOIN a.periodoEntregaDescripcion p " +
            "JOIN a.tipoProgramacionDescripcion t " + // Agregado
            "JOIN a.estadoVigiladoDescripcion e " + // Agregado
            "JOIN a.estadoRequerimientoDescripcion r " + // Agregado
            "LEFT JOIN a.delegaturas d " + //relaciones
            "LEFT JOIN a.digitoNIT n " + //relaciones
            "WHERE a.idRequerimiento = :idRequerimiento")
    List<GetMFRequerimientoProjection> findProjectionsByIdRequerimiento(Long idRequerimiento);
}
