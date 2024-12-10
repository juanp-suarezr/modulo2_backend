package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoResultadoIntegralORI;
import com.mf.mf.model.excel.MFEstadoSituacionFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFEstadoResultadoIntegralORIRepository extends JpaRepository<MFEstadoResultadoIntegralORI, Long> {

}
