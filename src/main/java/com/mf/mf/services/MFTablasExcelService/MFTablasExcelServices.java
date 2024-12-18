package com.mf.mf.services.MFTablasExcelService;

import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVEmpresasProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.repository.MFExcelRepository.MFIdentificacionVigiladoRepository;
import com.mf.mf.repository.MUVconsultaRepository.MUVEmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MFTablasExcelServices {

    @Autowired
    private MFIdentificacionVigiladoRepository mfIdentificacionVigiladoRepository;

    //Obtener requerimientos para tabla principal
    public List<GetMFIdentificacionVigiladoProjection> obtenerIdentificacionVigiladoByNIT(Integer nit) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (nit == null) {
            System.out.println("Advertencia: nit empresa no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMFIdentificacionVigiladoProjection> tipoVigiladoInfo = mfIdentificacionVigiladoRepository.findMFIdentificacionVigiladosByNit(nit);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (tipoVigiladoInfo.isEmpty()) {

            System.out.println("Advertencia: No se encontraron registros para el nit: " + nit);
            return Collections.emptyList();
        }

        return tipoVigiladoInfo;
    }



    }
