package com.mf.mf.repository.MFHeredadosRepository;

import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashHeredadosProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MFHeredadosRepository extends JpaRepository<MFHashHeredado, Long>  {
    boolean existsByIdProgramacionAndIdVigilado(Long idProgramacion, Integer idVigilado);

    @Query("SELECT h FROM MFHashHeredado h WHERE h.idProgramacion = :idProgramacion")
    List<GetMFHashHeredadosProjection> findHeredadosByIdProgramacion(@Param("idProgramacion") Long idProgramacion);

    @Modifying
    @Query("UPDATE MFHashHeredado m " +
                  "SET m.estadoEntrega = " +
                  "    CASE " +
                  "        WHEN m.estadoEntrega = 285 THEN :nuevoEstado " +
                  "        WHEN m.estadoEntrega = 289 THEN 284 " +
                  "    END " +
                  "WHERE m.fechaEntrega < :fechaActual " +
                  "AND m.estadoEntrega IN (285, 289)")
    void actualizarEstadoPorFechaEntrega(@Param("nuevoEstado") Integer nuevoEstado, @Param("fechaActual") LocalDate fechaActual);

    @Modifying
    @Query("UPDATE MFHashHeredado m " +
            "SET m.estadoEntrega = :estado " +
            "WHERE m.idHeredado = :idHeredado")
    void actualizarEstadoEntrega(@Param("idHeredado") Integer id, @Param("estado") Integer estado);

    //heredado by id
    @Query("SELECT h FROM MFHashHeredado h WHERE h.idHeredado = :idHeredado")
    Optional<MFHashHeredado> findByIdHeredado(@Param("idHeredado") Integer idHeredado);



}
