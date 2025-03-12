package com.mf.mf.repository.MFAnulacion;

import com.mf.mf.model.anulacion.MFAnexosAnulacion;
import com.mf.mf.model.anulacion.MFSolicitudAnulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFAnexoAnulacionRepository extends JpaRepository<MFAnexosAnulacion, Long>  {



}
