package com.mf.mf.repository.MUVconsultaRepository;

import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MUVEmpresas;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVEmpresasProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MUVEmpresasRepository extends JpaRepository<MUVEmpresas, Long> {

    @Query("SELECT e.id as id, " +
            "e.nit as nit, " +
            "e.razonSocial as razonSocial, " +
            "e.estado as estado " +
            "FROM MUVEmpresas e " +
            "WHERE e.nit = :nit")
    List<GetMUVEmpresasProjection> findProjectionsEmpresasByNIT(String nit);

    @Query("SELECT e.id as id, " +
            "e.nit as nit, " +
            "e.razonSocial as razonSocial, " +
            "e.estado as estado " +
            "FROM MUVEmpresas e ")
    List<GetMUVEmpresasProjection> findProjectionsEmpresas();

}
