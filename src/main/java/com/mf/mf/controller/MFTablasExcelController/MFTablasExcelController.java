package com.mf.mf.controller.MFTablasExcelController;

import com.mf.mf.projection.MFExcelProjection.*;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.services.MFRequerimientoServices.MUVEmpresasServices;
import com.mf.mf.services.MFRequerimientoServices.MUVTipoVigiladoServices;
import com.mf.mf.services.MFTablasExcelService.MFTablasExcelServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/muv")
@Tag(name = "TABLAS EXCEL", description = "obtener información cargada en los excel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MFTablasExcelController {

    @Autowired
    private MFTablasExcelServices mfTablasExcelServices;

    //Obtener TIPO VIGILADOS
    @GetMapping("/indentificacion-vigilado")
    public List<GetMFIdentificacionVigiladoProjection> obtenerTipoVigilados(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerIdentificacionVigiladoByNIT(nit, idHeredado);
    }

    //Obtener ESTADOS FINANCIEROS
    @GetMapping("/ESF")
    public List<GetMFEstadoSituacionFinancieraProjection> obtenerESF(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerESFByNIT(nit, idHeredado);
    }

    //Obtener ESTADOS RESULTADOS
    @GetMapping("/ER")
    public List<GetMFEstadoResultadosProjection> obtenerER(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerERByNIT(nit, idHeredado);
    }

    //Obtener ESTADOS RESULTADOS ORI
    @GetMapping("/ORI")
    public List<GetMFEstadoResultadoIntegralORIProjection> obtenerORI(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerORIByNIT(nit, idHeredado);
    }

    //Obtener ESTADOS FLUJO EFECTIVO INDIRECTO
    @GetMapping("/EFEIndirecto")
    public List<GetMFEstadoFlujoIndirectoProjection> obtenerEFEIndirecto(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerEFEIndirectoByNIT(nit, idHeredado);
    }

    //Obtener ESTADOS FLUJO EFECTIVO DIRECTO
    @GetMapping("/EFEDirecto")
    public List<GetMFEstadoFlujoDirectoProjection> obtenerEFEDirecto(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerEFEDirectoByNIT(nit, idHeredado);
    }

    //Obtener ESTADOS DICTAMEN
    @GetMapping("/dictamen")
    public List<GetMFDictamenRevisorFiscalProjection> obtenerDictamen(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerDictamenByNIT(nit, idHeredado);
    }




}
