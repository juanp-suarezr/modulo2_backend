package com.mf.mf.controller.MUVconsultaController;

import com.mf.mf.projection.MFRequerimientoProjection.GetMUVEmpresasProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.services.MFRequerimientoServices.MUVEmpresasServices;
import com.mf.mf.services.MFRequerimientoServices.MUVTipoVigiladoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/muv")
@Tag(name = "Entidad MUV", description = "consultas de modulo vigilados")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MUVconsultaController {

    @Autowired
    private MUVTipoVigiladoServices muvTipoVigiladoServices;
    @Autowired
    private MUVEmpresasServices muvEmpresasServices;

    //Obtener TIPO VIGILADOS
    @GetMapping("/tipo-vigilado/")
    public List<GetMUVTipoVigiladoProjection> obtenerTipoVigilados(@RequestParam(required = false) Integer idDelegatura) {
        if (idDelegatura == null) {
            throw new RuntimeException("Error: El parámetro 'idDelegatura' no se envió.");
        }
        return muvTipoVigiladoServices.obtenerTipoVigilados(idDelegatura);
    }

    //Obtener Empresas by NIT
    @GetMapping("/empresasByNit/")
    public List<GetMUVEmpresasProjection> obtenerEmpresas(@RequestParam(required = false) String nit) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return muvEmpresasServices.obtenerEmpresasByNIT(nit);
    }

}
