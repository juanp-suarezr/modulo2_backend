package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFEstadoFlujoEfectivoDirecto;
import com.mf.mf.model.excel.MFEstadoFlujoEfectivoIndirecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFEstadoFlujoEfectivoDirectoRepository extends JpaRepository<MFEstadoFlujoEfectivoDirecto, Long> {

}
