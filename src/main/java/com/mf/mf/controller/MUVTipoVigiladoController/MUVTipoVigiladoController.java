package com.mf.mf.controller.MUVTipoVigiladoController;

import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosTableProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.services.MFRequerimientoServices.MFRequerimientoServices;
import com.mf.mf.services.MFRequerimientoServices.MUVTipoVigiladoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/muv")
@Tag(name = "Entidad MUV", description = "consultas de modulo vigilados")
@CrossOrigin(origins = "*")
public class MUVTipoVigiladoController {

    @Autowired
    private MUVTipoVigiladoServices muvTipoVigiladoServices;

    //Obtener TIPO VIGILADOS
    @CrossOrigin(origins = "", allowedHeaders = "")
    @GetMapping("/tipo-vigilado/")
    public List<GetMUVTipoVigiladoProjection> obtenerTipoVigilados(@RequestParam(required = false) Integer idDelegatura) {
        if (idDelegatura == null) {
            throw new RuntimeException("Error: El parámetro 'idDelegatura' no se envió.");
        }
        return muvTipoVigiladoServices.obtenerTipoVigilados(idDelegatura);
    }

}
