package com.mf.mf.repository.MFAnulacion;

import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.anulacion.MFAnexosAnulacion;
import com.mf.mf.model.anulacion.MFSolicitudAnulacion;
import com.mf.mf.projection.MFAnulacion.GetMFSolicitudAnulacionProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashHeredadosProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosEntregasProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MFSolicitudAnulacionRepository extends JpaRepository<MFSolicitudAnulacion, Long>  {

    boolean existsByIdHeredadoAndEstadoSolicitud(Integer idHeredado, String estadoSolicitud);

    //SOLICITUDES ANULACION
    @Query("SELECT s.id as id, " +
            "s.nombre as nombre, " +
            "s.razonSocial as razonSocial, " +
            "s.estadoSolicitud as estadoSolicitud, " +
            "s.fechaSolicitud as fechaSolicitud, " +
            "s.observacion as observacion, " +
            "h.idProgramacion as idProgramacion, " +
            "h.idHeredado as idHeredado, " +
            "h.individual as individual, " +
            "h.nit as nit, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "r.actoAdministrativo as actoAdministrativo " +


            "FROM MFSolicitudAnulacion s " +
            "LEFT JOIN MFHashHeredado h ON h.idHeredado = s.idHeredado " +
            "LEFT JOIN MFRequerimiento r ON h.idProgramacion = r.idRequerimiento "+
            " JOIN r.tipoRequerimientoDescripcion f " +
            "WHERE COALESCE(h.estadoEntrega, 0) IN (461) ")
    List<GetMFSolicitudAnulacionProjection> findMFSolicitudAnulacion();

    // Método para obtener todos los anexos relacionados con una solicitud específica
    @Query("SELECT s.id as id, " +
            "s.nombre as nombre, " +
            "s.razonSocial as razonSocial, " +
            "s.estadoSolicitud as estadoSolicitud, " +
            "s.fechaSolicitud as fechaSolicitud, " +
            "s.identificacion as identificacion, " +
            "s.email as email, " +
            "s.email1 as email1, " +
            "s.descripcion as descripcion, " +
            "s.peticion as peticion, " +
            "s.itemsModificados as itemsModificados, " +
            "s.observacion as observacion, " +
            "h.idProgramacion as idProgramacion, " +
            "h.idHeredado as idHeredado, " +
            "h.nit as nit, " +
            "h.estadoEntrega as estadoEntrega, " +
            "h.individual as individual, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "e.descripcion as estadoRequerimientoDescripcion, " +
            "r.fechaInicio as fechaInicio, " +
            "r.fechaFin as fechaFin, " +
            "r.periodoEntrega as periodoEntrega, " +
            "r.annioVigencia as annioVigencia, " +
            "r.fechaPublicacion as fechaPublicacion, " +
            "r.actoAdministrativo as actoAdministrativo " +

            "FROM MFSolicitudAnulacion s " +
            "LEFT JOIN MFHashHeredado h ON h.idHeredado = s.idHeredado " +
            "LEFT JOIN MFRequerimiento r ON h.idProgramacion = r.idRequerimiento "+
            " JOIN r.tipoRequerimientoDescripcion f " +
            "LEFT JOIN r.estadoRequerimientoDescripcion e " +
            "WHERE s.id = :idAnulacion ")
    List<GetMFSolicitudAnulacionProjection> findById(@Param("idAnulacion") Integer idAnulacion);

    //Actualizar estado y observaciones
    @Modifying
    @Query("UPDATE MFSolicitudAnulacion s SET s.estadoSolicitud = :estadoReq, s.observacion = :observacion WHERE s.id = :id")
    void actualizarSolicitudAnulacion(@Param("estadoReq") String estadoReq, @Param("observacion") String observacion, @Param("id") Long id);



}
