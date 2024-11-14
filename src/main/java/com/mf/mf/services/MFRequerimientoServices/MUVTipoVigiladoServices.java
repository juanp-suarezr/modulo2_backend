package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.projection.MFRequerimientoProjection.*;
import com.mf.mf.repository.MUVTipoVigiladoRepository.MUVTipoVigiladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Service
public class MUVTipoVigiladoServices {

    @Autowired
    private MUVTipoVigiladoRepository muvTipoVigiladoRepository;

    //Obtener requerimientos para tabla principal
    public List<GetMUVTipoVigiladoProjection> obtenerTipoVigilados(Integer idDelegatura) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (idDelegatura == null) {
            System.out.println("Advertencia: idDelegatura no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMUVTipoVigiladoProjection> tipoVigilados = muvTipoVigiladoRepository.findProjectionsByIdDlegatura(idDelegatura);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (tipoVigilados.isEmpty()) {
            System.out.println("Advertencia: No se encontraron registros para el idDelegatura: " + idDelegatura);
            return Collections.emptyList();
        }

        return tipoVigilados;
    }

    }

