package com.mf.mf.repository.MFExcelRepository;

import com.mf.mf.model.excel.MFIdentificacionVigilado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFIdentificacionVigiladoRepository extends JpaRepository<MFIdentificacionVigilado, Long> {

}