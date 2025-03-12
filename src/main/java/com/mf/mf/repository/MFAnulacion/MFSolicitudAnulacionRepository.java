package com.mf.mf.repository.MFAnulacion;

import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.anulacion.MFSolicitudAnulacion;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashHeredadosProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MFSolicitudAnulacionRepository extends JpaRepository<MFSolicitudAnulacion, Long>  {



}
