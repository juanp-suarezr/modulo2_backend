package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.projection.MFRequerimientoProjection.GetMUVEmpresasProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.repository.MUVconsultaRepository.MUVEmpresasRepository;
import com.mf.mf.repository.MUVconsultaRepository.MUVTipoVigiladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MUVEmpresasServices {

    @Autowired
    private MUVEmpresasRepository muvEmpresasRepository;

    //Obtener requerimientos para tabla principal
    public List<GetMUVEmpresasProjection> obtenerEmpresasByNIT(String nit) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (nit == null) {
            System.out.println("Advertencia: nit empresa no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMUVEmpresasProjection> empresas = muvEmpresasRepository.findProjectionsEmpresasByNIT(nit);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (empresas.isEmpty()) {

            System.out.println("Advertencia: No se encontraron registros para el nit: " + nit);
            return Collections.emptyList();
        }

        return empresas;
    }

    }

