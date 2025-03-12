package com.mf.mf.repository.MFAnulacion;

import com.mf.mf.model.MFHashHeredado;
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

    //SOLICITUDES ANULACION
    @Query("SELECT s.id as id, " +
            "s.nombre as nombreRep, " +
            "s.razonSocial as razonSocial, " +
            "s.estadoSolicitud as estadoSolicitud, " +
            "s.fechaSolicitud as fechaSolicitud, " +
            "h.idProgramacion as idProgramacion, " +
            "h.idHeredado as idHeredado, " +
            "h.nit as nit, " +
            "f.descripcion as tipoRequerimientoDescripcion, " +
            "r.actoAdministrativo as actoAdministrativo " +

            "FROM MFSolicitudAnulacion s " +
            "LEFT JOIN MFHashHeredado h ON h.idHeredado = s.idHeredado " +
            "LEFT JOIN MFRequerimiento r ON h.idProgramacion = r.idRequerimiento "+
            " JOIN r.tipoRequerimientoDescripcion f " +
            "WHERE COALESCE(h.estadoEntrega, 0) IN (461) ")

    List<GetMFSolicitudAnulacionProjection> findMFSolicitudAnulacion();
}
