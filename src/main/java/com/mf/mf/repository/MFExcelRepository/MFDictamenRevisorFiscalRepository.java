package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFDictamenRevisorFiscal;
import com.mf.mf.model.excel.MFEstadoFlujoEfectivoDirecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFDictamenRevisorFiscalRepository extends JpaRepository<MFDictamenRevisorFiscal, Long> {

}
