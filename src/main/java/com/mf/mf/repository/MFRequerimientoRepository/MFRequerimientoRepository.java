package com.mf.mf.repository.MFRequerimientoRepository;

import com.mf.mf.model.MFRequerimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFRequerimientoRepository extends JpaRepository<MFRequerimiento, Long> {


}
