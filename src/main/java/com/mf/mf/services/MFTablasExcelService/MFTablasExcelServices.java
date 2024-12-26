package com.mf.mf.services.MFTablasExcelService;

import com.mf.mf.projection.MFExcelProjection.GetMFEstadoResultadoIntegralORIProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoResultadosProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFEstadoSituacionFinancieraProjection;
import com.mf.mf.projection.MFExcelProjection.GetMFIdentificacionVigiladoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVEmpresasProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.repository.MFExcelRepository.MFEstadoResultadoIntegralORIRepository;
import com.mf.mf.repository.MFExcelRepository.MFEstadoResultadoRepository;
import com.mf.mf.repository.MFExcelRepository.MFEstadoSituacionFinancieraRepository;
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
    @Autowired
    private MFEstadoSituacionFinancieraRepository mfEstadoSituacionFinancieraRepository;
    @Autowired
    private MFEstadoResultadoRepository mfEstadoResultadoRepository;
    @Autowired
    private MFEstadoResultadoIntegralORIRepository mfEstadoResultadoIntegralORIRepository;

    //Obtener info IDENTIFICACION VIGILADO para tabla principal
    public List<GetMFIdentificacionVigiladoProjection> obtenerIdentificacionVigiladoByNIT(Integer nit, Integer idHeredado) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (nit == null) {
            System.out.println("Advertencia: nit empresa no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMFIdentificacionVigiladoProjection> tipoVigiladoInfo = mfIdentificacionVigiladoRepository.findMFIdentificacionVigiladosByNit(nit, idHeredado);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (tipoVigiladoInfo.isEmpty()) {

            System.out.println("Advertencia: No se encontraron registros para el nit: " + nit);
            return Collections.emptyList();
        }

        return tipoVigiladoInfo;
    }

    //Obtener info ESF para tabla principal
    public List<GetMFEstadoSituacionFinancieraProjection> obtenerESFByNIT(Integer nit, Integer idHeredado) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (nit == null) {
            System.out.println("Advertencia: nit empresa no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMFEstadoSituacionFinancieraProjection> tipoVigiladoInfo = mfEstadoSituacionFinancieraRepository.findMFESFByNit(nit, idHeredado);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (tipoVigiladoInfo.isEmpty()) {

            System.out.println("Advertencia: No se encontraron registros para el nit: " + nit);
            return Collections.emptyList();
        }

        return tipoVigiladoInfo;
    }

    //Obtener info ER para tabla principal
    public List<GetMFEstadoResultadosProjection> obtenerERByNIT(Integer nit, Integer idHeredado) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (nit == null) {
            System.out.println("Advertencia: nit empresa no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMFEstadoResultadosProjection> tipoVigiladoInfo = mfEstadoResultadoRepository.findMFERByNit(nit, idHeredado);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (tipoVigiladoInfo.isEmpty()) {

            System.out.println("Advertencia: No se encontraron registros para el nit: " + nit);
            return Collections.emptyList();
        }

        return tipoVigiladoInfo;
    }

    //Obtener info ER para tabla principal
    public List<GetMFEstadoResultadoIntegralORIProjection> obtenerORIByNIT(Integer nit, Integer idHeredado) {
        // Si no se proporciona idDelegatura, retornar una lista vacía
        if (nit == null) {
            System.out.println("Advertencia: nit empresa no proporcionado");
            return Collections.emptyList(); // Retorna una lista vacía
        }

        // Intentar obtener los registros
        List<GetMFEstadoResultadoIntegralORIProjection> tipoVigiladoInfo = mfEstadoResultadoIntegralORIRepository.findMFORIByNit(nit, idHeredado);

        // Retornar lista vacía si no se encuentran registros, con mensaje de advertencia
        if (tipoVigiladoInfo.isEmpty()) {

            System.out.println("Advertencia: No se encontraron registros para el nit: " + nit);
            return Collections.emptyList();
        }

        return tipoVigiladoInfo;
    }



}

