package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosEntregasProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosTableProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
            "r.descripcion as estadoRequerimientoDescripcion " + // Agregado
            "FROM MFRequerimiento a " +
            "JOIN a.tipoRequerimientoDescripcion f " +
            "JOIN a.periodoEntregaDescripcion p " +
            "JOIN a.tipoProgramacionDescripcion t " + // Agregado
            "JOIN a.estadoVigiladoDescripcion e " + // Agregado
            "JOIN a.estadoRequerimientoDescripcion r " + // Agregado
            "WHERE a.idRequerimiento = :idRequerimiento")
    List<GetMFRequerimientoProjection> findProjectionsByIdRequerimiento(Long idRequerimiento);

    //ENTREGAS PENDIENTES
    @Query("SELECT r.idRequerimiento as idRequerimiento, " +
            "r.nombreRequerimiento as nombreRequerimiento, " +
            "r.fechaInicio as fechaInicio, " +
            "r.fechaFin as fechaFin, " +
            "r.actoAdministrativo as actoAdministrativo, " +
            "r.annioVigencia as annioVigencia, " +
            "r.tipoProgramacion as tipoProgramacion, " +
            "r.periodoEntrega as periodoEntrega, " +
            "r.fechaPublicacion as fechaPublicacion, " +
            "h.idProgramacion as idProgramacion, " +
            "h.individual as individual, " +
            "h.estadoEntrega as estadoEntrega, " +
            "h.idHeredado as idHeredado, " +
            "h.cargoExcel as hasExcel, " +
            "h.nit as nit, " +
            "h.fechaEntrega as fechaEntrega, " +
            "d.idNumeroDigitos as idNumeroDigitos, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "p.descripcion as periodoEntregaDescripcion, " +
            "e.descripcion as estadoRequerimientoDescripcion, " +
            "s.estadoSolicitud as estadoAnulacion, " +
            "a.fechaEntrega as fechaReporte " +
            "FROM MFRequerimiento r " +
            "LEFT JOIN MFHashHeredado h ON " +
            "   ((h.tipoProgramacion = 231 AND h.idProgramacion IN (SELECT del.idProgramacion FROM MFHashDelegatura del WHERE del.idRequerimiento = r.idRequerimiento)) OR " +
            "    (h.tipoProgramacion = 233 AND h.idProgramacion IN (SELECT d.idProgramacion FROM MFHashDigitoNIT d WHERE d.idRequerimiento = r.idRequerimiento)) OR " +
            "    (h.tipoProgramacion = 232 AND h.idProgramacion = r.idRequerimiento)) " +
            "LEFT JOIN r.tipoRequerimientoDescripcion f " +
            "LEFT JOIN r.estadoRequerimientoDescripcion e " +
            "LEFT JOIN r.periodoEntregaDescripcion p " +
            "LEFT JOIN MFAnexos a ON h.idHeredado = a.idHeredado " +
            "LEFT JOIN MFSolicitudAnulacion s ON h.idHeredado = s.idHeredado " +
            "LEFT JOIN MFHashDigitoNIT d ON r.idRequerimiento = d.idRequerimiento " +
            "WHERE (h.estadoEntrega NOT IN (284, 460,  461, 462) " +
            "       OR (h.estadoEntrega = 460 AND s IS NOT NULL AND s.estadoSolicitud = 'Aprobado')) " +
            "AND h.nit = :nitUsuario")
    List<GetMFRequerimientosEntregasProjection> findEntregasPendientesByNIT(@Param("nitUsuario") Integer nitUsuario);



    //ENTREGAS FINALIZADAS
    @Query("SELECT r.idRequerimiento as idRequerimiento, " +
            "r.nombreRequerimiento as nombreRequerimiento, " +
            "r.fechaInicio as fechaInicio, " +
            "r.fechaFin as fechaFin, " +
            "r.actoAdministrativo as actoAdministrativo, " +
            "r.annioVigencia as annioVigencia, " +
            "r.tipoProgramacion as tipoProgramacion, " +
            "r.periodoEntrega as periodoEntrega, " +
            "r.fechaPublicacion as fechaPublicacion, " +
            "h.idProgramacion as idProgramacion, " +
            "h.individual as individual, " +
            "h.fechaEntrega as fechaEntrega, " +
            "h.idHeredado as idHeredado, " +
            "h.cargoExcel as hasExcel, " +
            "h.estadoEntrega as estadoEntrega, " +
            "h.nit as nit, " +
            "d.idNumeroDigitos as idNumeroDigitos, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "e.descripcion as estadoRequerimientoDescripcion, " +
            "s.estadoSolicitud as estadoAnulacion, " +
            "a.fechaEntrega as fechaReporte " +

            "FROM MFRequerimiento r " +
            "LEFT JOIN MFHashHeredado h ON " +
            "   ((h.tipoProgramacion = 231 AND h.idProgramacion IN (SELECT del.idProgramacion FROM MFHashDelegatura del WHERE del.idRequerimiento = r.idRequerimiento)) OR " +
            "    (h.tipoProgramacion = 233 AND h.idProgramacion IN (SELECT d.idProgramacion FROM MFHashDigitoNIT d WHERE d.idRequerimiento = r.idRequerimiento)) OR " +
            "    (h.tipoProgramacion = 232 AND h.idProgramacion = r.idRequerimiento)) " +
            "LEFT JOIN MFAnexos a ON h.idHeredado = a.idHeredado " +
            "LEFT JOIN MFSolicitudAnulacion s ON h.idHeredado = s.idHeredado " +
            " JOIN r.tipoRequerimientoDescripcion f " +
            "LEFT JOIN r.estadoRequerimientoDescripcion e " +
            "LEFT JOIN MFHashDigitoNIT d ON r.idRequerimiento = d.idRequerimiento " +
            "WHERE COALESCE(h.estadoEntrega, 0) IN (284, 460, 461, 462)  " +
            "AND s.estadoSolicitud IS NULL " +
            "AND h.nit = :nitUsuario")
    List<GetMFRequerimientosEntregasProjection> findEntregasByNIT(@Param("nitUsuario") Integer nitUsuario);

    @Modifying
    @Query("UPDATE MFRequerimiento m SET m.estadoRequerimiento = :estadoReq WHERE m.fechaFin < :fechaActual")
    void actualizarEstadoPorFechaEntrega(@Param("estadoReq") Integer estadoReq, @Param("fechaActual") LocalDate fechaActual);

}
