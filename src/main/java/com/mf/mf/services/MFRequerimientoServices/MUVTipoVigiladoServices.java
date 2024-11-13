package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.projection.MFRequerimientoProjection.*;
import com.mf.mf.repository.MUVTipoVigiladoRepository.MUVTipoVigiladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MUVTipoVigiladoServices {

    @Autowired
    private MUVTipoVigiladoRepository muvTipoVigiladoRepository;

    //Obtener requerimientos para tabla principal
    public List<GetMUVTipoVigiladoProjection> obtenerTipoVigilados(Integer idDelegatura) {
        List<GetMUVTipoVigiladoProjection> tipoVigilados = muvTipoVigiladoRepository.findProjectionsByIdDlegatura(idDelegatura);

        try {
            if (tipoVigilados.isEmpty()) {
                throw new RuntimeException("Error: No se encontraron registros para el idDelegatura: " + idDelegatura);
            }
            return tipoVigilados;
        } catch (Exception e) {
            throw new RuntimeException("Error: No se encontró el tipo de vigilado" + idDelegatura, e);
        }

    }
}
