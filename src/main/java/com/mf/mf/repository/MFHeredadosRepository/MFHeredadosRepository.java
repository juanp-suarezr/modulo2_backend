package com.mf.mf.repository.MFHeredadosRepository;

import com.mf.mf.model.MFHashHeredado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFHeredadosRepository extends JpaRepository<MFHashHeredado, Long>  {
    boolean existsByIdProgramacionAndIdVigilado(Long idProgramacion, Integer idVigilado);
}
