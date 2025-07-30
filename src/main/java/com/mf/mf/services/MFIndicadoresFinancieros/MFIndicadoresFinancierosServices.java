package com.mf.mf.services.MFIndicadoresFinancieros;

import com.mf.mf.dto.MFIndicadoresFinancieros.MFIndicadoresFinancierosDTO;
import com.mf.mf.projection.MFExcelProjection.*;
import com.mf.mf.repository.MFExcelRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MFIndicadoresFinancierosServices {

    @Autowired
    private MFIdentificacionVigiladoRepository mfIdentificacionVigiladoRepository;
    @Autowired
    private MFEstadoSituacionFinancieraRepository mfEstadoSituacionFinancieraRepository;
    @Autowired
    private MFEstadoResultadoRepository mfEstadoResultadoRepository;
    @Autowired
    private MFEstadoResultadoIntegralORIRepository mfEstadoResultadoIntegralORIRepository;
    @Autowired
    private MFEstadoFlujoIndirectoRepository mfEstadoFlujoIndirectoRepository;
    @Autowired
    private MFEstadoFlujoDirectoRepository mfEstadoFlujoDirectoRepository;
    @Autowired
    private MFDictamenRevisorFiscalRepository mfDictamenRevisorFiscalRepository;


    public MFIndicadoresFinancierosDTO obtenerIndicadoresFinancieros(Integer nit) {
        GetMFEstadoSituacionFinancieraProjection datos = mfEstadoSituacionFinancieraRepository.findUltimoRegistroPorNit(nit);
        GetMFEstadoResultadosProjection datosER = mfEstadoResultadoRepository.findUltimoRegistroPorNit(nit);

        if (datos == null) return null;

        Long ac = datos.getTotalActivosCorrientes();
        Long anc = datos.getTotalActivosNoCorrientes();
        Long pc = datos.getPasivosCorrientes();
        Long pnc = datos.getPasivosNoCorrientes();
        Long gananciaNeta = datosER.getGananciaNeta();
        Long ingresoOperacional = datosER.getIngresosActividadesOrdinarias();
        Long uitlidadOperacional = datosER.getGananciaOperacion();
        Long capitalTrabajo = ac - pc;

        Long totalActivos = (ac != null ? ac : 0L) + (anc != null ? anc : 0L);
        Long totalPasivos = (pc != null ? pc : 0L) + (pnc != null ? pnc : 0L);

        double liquidez = (pc != null && pc != 0) ? Math.round((double) (ac != null ? ac : 0L) / pc) : 0;
        double solvencia = (totalPasivos != 0) ? Math.round((double) totalActivos / totalPasivos) : 0;
        double rentaNeta = Math.round((double) gananciaNeta / ingresoOperacional);
        double rentaOperacional = Math.round((double) uitlidadOperacional / ingresoOperacional);

        System.out.println(capitalTrabajo);
        System.out.println(gananciaNeta);
        System.out.println(ingresoOperacional);
        System.out.println(rentaNeta);
        System.out.println(rentaOperacional);

        return new MFIndicadoresFinancierosDTO(ac, pc, capitalTrabajo, liquidez, solvencia, rentaNeta, rentaOperacional);
    }




}

