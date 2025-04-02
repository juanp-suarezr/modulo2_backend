package com.mf.mf.controller.MFTablasExcelController;

import com.mf.mf.projection.MFExcelProjection.*;
import com.mf.mf.projection.MFRequerimientoProjection.GetMUVTipoVigiladoProjection;
import com.mf.mf.repository.MFExcelRepository.*;
import com.mf.mf.services.MFRequerimientoServices.MUVEmpresasServices;
import com.mf.mf.services.MFRequerimientoServices.MUVTipoVigiladoServices;
import com.mf.mf.services.MFTablasExcelService.MFTablasExcelServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/muv")
@Tag(name = "TABLAS EXCEL", description = "obtener información cargada en los excel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MFTablasExcelController {

    @Autowired
    private MFTablasExcelServices mfTablasExcelServices;
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

    //Obtener TIPO VIGILADOS
    @GetMapping("/indentificacion-vigilado")
    public List<GetMFIdentificacionVigiladoProjection> obtenerTipoVigilados(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerIdentificacionVigiladoByNIT(nit, idHeredado);
    }

    //Obtener historial -- rol misional -- TIPO VIGILADOS
    @GetMapping("/comparativo-identificacion-vigilado")
    public Map<String, Object> compararRegistros(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }

        // Obtener los registros correspondientes al NIT e ID heredado
        // Intentar obtener los registros

        List<GetMFIdentificacionVigiladoProjection> registros = mfIdentificacionVigiladoRepository.findMFIdentificacionVigiladosByNit1(nit, idHeredado);


        if (registros == null || registros.size() < 2) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        // Filtrar los registros por estado
        GetMFIdentificacionVigiladoProjection registroAntiguo = registros.stream()
                .filter(r -> !r.getEstado()) // Estado false
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro antiguo (estado = false)."));

        GetMFIdentificacionVigiladoProjection registroActualizado = registros.stream()
                .filter(r -> r.getEstado()) // Estado true
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro actualizado (estado = true)."));

        // Comparar los registros automáticamente usando reflexión
        Map<String, Object> cambios = new HashMap<>();
        try {
            for (var method : GetMFIdentificacionVigiladoProjection.class.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) { // Solo métodos "get"
                    String fieldName = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4); // Obtener el nombre del campo
                    Object valorAntiguo = method.invoke(registroAntiguo);
                    Object valorActualizado = method.invoke(registroActualizado);

                    if (valorAntiguo != null && !valorAntiguo.equals(valorActualizado)) {
                        cambios.put(fieldName, Map.of("antiguo", valorAntiguo, "actualizado", valorActualizado));
                    } else if (valorAntiguo == null && valorActualizado != null) {
                        cambios.put(fieldName, Map.of("antiguo", null, "actualizado", valorActualizado));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comparar los registros.", e);
        }

        return cambios; // Devuelve el mapa con los cambios al frontend
    }

    //Obtener ESTADOS FINANCIEROS
    @GetMapping("/ESF")
    public List<GetMFEstadoSituacionFinancieraProjection> obtenerESF(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerESFByNIT(nit, idHeredado);
    }

    //Obtener historial -- rol misional -- ESTADOS FINANCIEROS
    @GetMapping("/comparativo-estados-financieros")
    public Map<String, Object> compararEstadosFinancieros(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }

        // Obtener los registros correspondientes al NIT e ID heredado
        List<GetMFEstadoSituacionFinancieraProjection> registros = mfEstadoSituacionFinancieraRepository.findMFESFByNit1(nit, idHeredado);

        if (registros == null || registros.size() < 4) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        System.out.println(registros);
        // Filtrar los registros según las combinaciones de estado y actual
        GetMFEstadoSituacionFinancieraProjection estadoFalseActualFalse = registros.stream()
                .filter(r -> !r.getEstado() && !r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = false y actual = false."));

        GetMFEstadoSituacionFinancieraProjection estadoTrueActualTrue = registros.stream()
                .filter(r -> r.getEstado() && r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = true y actual = true."));

        GetMFEstadoSituacionFinancieraProjection estadoFalseActualTrue = registros.stream()
                .filter(r -> !r.getEstado() && r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = false y actual = true."));

        GetMFEstadoSituacionFinancieraProjection estadoTrueActualFalse = registros.stream()
                .filter(r -> r.getEstado() && !r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = true y actual = false."));

        // Comparar los registros automáticamente usando reflexión
        Map<String, Object> cambios = new HashMap<>();
        try {
            // Agregar los años al mapa de cambios
            cambios.put("ano_comparativo", estadoTrueActualFalse.getAnnio());
            cambios.put("ano_actual", estadoTrueActualTrue.getAnnio());

            for (var method : GetMFEstadoSituacionFinancieraProjection.class.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) { // Solo métodos "get"
                    String fieldName = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4); // Obtener el nombre del campo

                    // Comparar estadoFalseActualFalse con estadoTrueActualFalse ---> año anterior
                    Object valorAntiguo1 = method.invoke(estadoFalseActualFalse);
                    Object valorActualizado1 = method.invoke(estadoTrueActualFalse);
                    if (valorAntiguo1 != null && !valorAntiguo1.equals(valorActualizado1)) {
                        cambios.put(fieldName + "_ano_anterior", Map.of("antiguo", valorAntiguo1, "actualizado", valorActualizado1));
                    }

                    // Comparar estadoFalseActualTrue con estadoTrueActualTrue --> año actual
                    Object valorAntiguo2 = method.invoke(estadoFalseActualTrue);
                    Object valorActualizado2 = method.invoke(estadoTrueActualTrue);
                    if (valorAntiguo2 != null && !valorAntiguo2.equals(valorActualizado2)) {
                        cambios.put(fieldName + "_ano_actual", Map.of("antiguo", valorAntiguo2, "actualizado", valorActualizado2));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comparar los registros.", e);
        }

        return cambios; // Devuelve el mapa con los cambios al frontend
    }



    //Obtener ESTADOS RESULTADOS
    @GetMapping("/ER")
    public List<GetMFEstadoResultadosProjection> obtenerER(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerERByNIT(nit, idHeredado);
    }

    //Obtener historial -- rol misional -- ESTADOS RESULTADOS
    @GetMapping("/comparativo-estados-resultados")
    public Map<String, Object> compararEstadosResultados(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }

        // Obtener los registros correspondientes al NIT e ID heredado
        List<GetMFEstadoResultadosProjection> registros = mfEstadoResultadoRepository.findMFERByNit1(nit, idHeredado);

        if (registros == null || registros.size() < 4) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        System.out.println(registros);
        // Filtrar los registros según las combinaciones de estado y actual
        GetMFEstadoResultadosProjection estadoFalseActualFalse = registros.stream()
                .filter(r -> !r.getEstado() && !r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = false y actual = false."));

        GetMFEstadoResultadosProjection estadoTrueActualTrue = registros.stream()
                .filter(r -> r.getEstado() && r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = true y actual = true."));

        GetMFEstadoResultadosProjection estadoFalseActualTrue = registros.stream()
                .filter(r -> !r.getEstado() && r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = false y actual = true."));

        GetMFEstadoResultadosProjection estadoTrueActualFalse = registros.stream()
                .filter(r -> r.getEstado() && !r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = true y actual = false."));

        // Comparar los registros automáticamente usando reflexión
        Map<String, Object> cambios = new HashMap<>();
        try {
            // Agregar los años al mapa de cambios
            cambios.put("ano_comparativo", estadoTrueActualFalse.getAnnio());
            cambios.put("ano_actual", estadoTrueActualTrue.getAnnio());

            for (var method : GetMFEstadoResultadosProjection.class.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) { // Solo métodos "get"
                    String fieldName = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4); // Obtener el nombre del campo

                    // Comparar estadoFalseActualFalse con estadoTrueActualFalse ---> año anterior
                    Object valorAntiguo1 = method.invoke(estadoFalseActualFalse);
                    Object valorActualizado1 = method.invoke(estadoTrueActualFalse);
                    if (valorAntiguo1 != null && !valorAntiguo1.equals(valorActualizado1)) {
                        cambios.put(fieldName + "_ano_anterior", Map.of("antiguo", valorAntiguo1, "actualizado", valorActualizado1));
                    }

                    // Comparar estadoFalseActualTrue con estadoTrueActualTrue --> año actual
                    Object valorAntiguo2 = method.invoke(estadoFalseActualTrue);
                    Object valorActualizado2 = method.invoke(estadoTrueActualTrue);
                    if (valorAntiguo2 != null && !valorAntiguo2.equals(valorActualizado2)) {
                        cambios.put(fieldName + "_ano_actual", Map.of("antiguo", valorAntiguo2, "actualizado", valorActualizado2));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comparar los registros.", e);
        }

        return cambios; // Devuelve el mapa con los cambios al frontend
    }


    //Obtener ESTADOS RESULTADOS ORI
    @GetMapping("/ORI")
    public List<GetMFEstadoResultadoIntegralORIProjection> obtenerORI(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerORIByNIT(nit, idHeredado);
    }

    //Obtener historial -- rol misional -- ESTADOS RESULTADOS ORI
    @GetMapping("/comparativo-estados-resultados-ori")
    public Map<String, Object> compararEstadosResultadosORI(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }

        // Obtener los registros correspondientes al NIT e ID heredado
        List<GetMFEstadoResultadoIntegralORIProjection> registros = mfEstadoResultadoIntegralORIRepository.findMFORIByNit1(nit, idHeredado);

        if (registros == null || registros.size() < 4) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        System.out.println(registros);
        // Filtrar los registros según las combinaciones de estado y actual
        GetMFEstadoResultadoIntegralORIProjection estadoFalseActualFalse = registros.stream()
                .filter(r -> !r.getEstado() && !r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = false y actual = false."));

        GetMFEstadoResultadoIntegralORIProjection estadoTrueActualTrue = registros.stream()
                .filter(r -> r.getEstado() && r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = true y actual = true."));

        GetMFEstadoResultadoIntegralORIProjection estadoFalseActualTrue = registros.stream()
                .filter(r -> !r.getEstado() && r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = false y actual = true."));

        GetMFEstadoResultadoIntegralORIProjection estadoTrueActualFalse = registros.stream()
                .filter(r -> r.getEstado() && !r.getActual())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro con estado = true y actual = false."));

        // Comparar los registros automáticamente usando reflexión
        Map<String, Object> cambios = new HashMap<>();
        try {
            // Agregar los años al mapa de cambios
            cambios.put("ano_comparativo", estadoTrueActualFalse.getAnnio());
            cambios.put("ano_actual", estadoTrueActualTrue.getAnnio());

            for (var method : GetMFEstadoResultadoIntegralORIProjection.class.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) { // Solo métodos "get"
                    String fieldName = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4); // Obtener el nombre del campo

                    // Comparar estadoFalseActualFalse con estadoTrueActualFalse ---> año anterior
                    Object valorAntiguo1 = method.invoke(estadoFalseActualFalse);
                    Object valorActualizado1 = method.invoke(estadoTrueActualFalse);
                    if (valorAntiguo1 != null && !valorAntiguo1.equals(valorActualizado1)) {
                        cambios.put(fieldName + "_ano_anterior", Map.of("antiguo", valorAntiguo1, "actualizado", valorActualizado1));
                    }

                    // Comparar estadoFalseActualTrue con estadoTrueActualTrue --> año actual
                    Object valorAntiguo2 = method.invoke(estadoFalseActualTrue);
                    Object valorActualizado2 = method.invoke(estadoTrueActualTrue);
                    if (valorAntiguo2 != null && !valorAntiguo2.equals(valorActualizado2)) {
                        cambios.put(fieldName + "_ano_actual", Map.of("antiguo", valorAntiguo2, "actualizado", valorActualizado2));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comparar los registros.", e);
        }

        return cambios; // Devuelve el mapa con los cambios al frontend
    }


    //Obtener ESTADOS FLUJO EFECTIVO INDIRECTO
    @GetMapping("/EFEIndirecto")
    public List<GetMFEstadoFlujoIndirectoProjection> obtenerEFEIndirecto(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }
        return mfTablasExcelServices.obtenerEFEIndirectoByNIT(nit, idHeredado);
    }

    //Obtener historial -- rol misional -- EFECTIVO INDIRECTO
    @GetMapping("/comparativo-EFEIndirecto")
    public Map<String, Object> compararEFEIndirecto(@RequestParam Integer nit, @RequestParam Integer idHeredado) {
        if (nit == null) {
            throw new RuntimeException("Error: El parámetro 'nit' no se envió.");
        }

        // Obtener los registros correspondientes al NIT e ID heredado
        // Intentar obtener los registros

        List<GetMFEstadoFlujoIndirectoProjection> registros = mfEstadoFlujoIndirectoRepository.findMFEFEIndirectoByNit1(nit, idHeredado);


        if (registros == null || registros.size() < 2) {
            throw new RuntimeException("Error: No se encontraron suficientes registros para comparar.");
        }

        // Filtrar los registros por estado
        GetMFEstadoFlujoIndirectoProjection registroAntiguo = registros.stream()
                .filter(r -> !r.getEstado()) // Estado false
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro antiguo (estado = false)."));

        GetMFEstadoFlujoIndirectoProjection registroActualizado = registros.stream()
                .filter(r -> r.getEstado()) // Estado true
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: No se encontró un registro actualizado (estado = true)."));

        // Comparar los registros automáticamente usando reflexión
        Map<String, Object> cambios = new HashMap<>();
        try {
            cambios.put("annio", registroActualizado.getAnnio());
            for (var method : GetMFEstadoFlujoIndirectoProjection.class.getDeclaredMethods()) {
                if (method.getName().startsWith("get")) { // Solo métodos "get"
                    String fieldName = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4); // Obtener el nombre del campo
                    Object valorAntiguo = method.invoke(registroAntiguo);
                    Object valorActualizado = method.invoke(registroActualizado);

                    if (valorAntiguo != null && !valorAntiguo.equals(valorActualizado)) {
                        cambios.put(fieldName, Map.of("antiguo", valorAntiguo, "actualizado", valorActualizado));
                    } else if (valorAntiguo == null && valorActualizado != null) {
                        cambios.put(fieldName, Map.of("antiguo", null, "actualizado", valorActualizado));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al comparar los registros.", e);
        }

        return cambios; // Devuelve el mapa con los cambios al frontend
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
