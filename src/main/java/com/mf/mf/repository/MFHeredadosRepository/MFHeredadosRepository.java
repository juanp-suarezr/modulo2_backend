package com.mf.mf.repository.MFHeredadosRepository;

import com.mf.mf.model.MFHashHeredado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MFHeredadosRepository extends JpaRepository<MFHashHeredado, Long>  {
    boolean existsByIdProgramacionAndIdVigilado(Long idProgramacion, Integer idVigilado);

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
            "SET m.estadoEntrega = 289 " +
            "WHERE m.idHeredado = :idHeredado")
    void actualizarEstadoEntrega(@Param("idHeredado") Integer id);


}
