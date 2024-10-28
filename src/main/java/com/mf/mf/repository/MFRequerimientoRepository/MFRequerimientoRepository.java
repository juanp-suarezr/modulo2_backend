package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFRequerimiento;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MFRequerimientoRepository extends JpaRepository<MFRequerimiento, Long> {

    @Query("SELECT a FROM MFRequerimiento a")
    List<GetMFRequerimientoProjection> findAllProjections();

    List<GetMFRequerimientoProjection> findProjectionsByIdRequerimiento(Long idRequerimiento);

}
