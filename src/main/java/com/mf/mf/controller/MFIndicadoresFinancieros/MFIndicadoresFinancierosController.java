package com.mf.mf.controller.MFIndicadoresFinancieros;

import com.mf.mf.dto.MFIndicadoresFinancieros.MFIndicadoresFinancierosDTO;
import com.mf.mf.services.MFIndicadoresFinancieros.MFIndicadoresFinancierosServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/indicadores-gestion")
@Tag(name = "Indicadores financieros", description = "Generador de indicadores financieros para Sustentabilidad Financiera Y Visitas de inspección")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MFIndicadoresFinancierosController {


    private final MFIndicadoresFinancierosServices mfIndicadoresFinancierosServices;

    @GetMapping("/{nit}")
    public ResponseEntity<?> obtenerIndicadores(@PathVariable Integer nit) {
        MFIndicadoresFinancierosDTO indicadores = mfIndicadoresFinancierosServices.obtenerIndicadoresFinancieros(nit);

        if (indicadores == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron datos financieros para el NIT: " + nit);
        }

        return ResponseEntity.ok(indicadores);
    }

}
