package com.mf.mf.repository.MUVTipoVigiladoRepository;

import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MUVTipoVigiladoRepository extends JpaRepository<MFHashDelegatura, Long> {

    @Query("SELECT t.id as id, " +
            "t.idDelegatura as idDelegatura, " +
            "t.id_grupo as idGrupo, " +
            "t.sigla as sigla, " +
            "t.descripcion as descripcion, " +
            "t.estado as estado " +
            "FROM MUVTipoVigilado t " +
            "WHERE t.idDelegatura = :idDelegatura")
    List<GetMUVTipoVigiladoProjection> findProjectionsByIdDlegatura(Integer idDelegatura);

}
