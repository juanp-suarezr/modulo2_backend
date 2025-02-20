package com.mf.mf.controller.MFRequerimientoController;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.dto.MFRequerimientoWithHashDTO;
import com.mf.mf.payload.ApiResponse;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosEntregasProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosTableProjection;
import com.mf.mf.repository.MFRequerimientoRepository.MFRequerimientoRepository;
import com.mf.mf.services.MFRequerimientoServices.MFRequerimientoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/requerimiento")
@Tag(name = "Entidad Entregas", description = "obtener listado de entregas pendientes o finalizadas, segun nit")
@CrossOrigin(origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MFEntregasController {

    @Autowired
    private MFRequerimientoServices requerimientoServices;
    @Autowired
    private MFRequerimientoRepository repository;


    //Obtener entregas pendientes por nit
    @GetMapping("/entregas-pendientes")
    public ResponseEntity<List<GetMFRequerimientosEntregasProjection>> getEntregaPendienteByNIT(@RequestParam Integer nit) {
        try {
            List<GetMFRequerimientosEntregasProjection> entrega = repository.findEntregasPendientesByNIT(nit);
            return ResponseEntity.ok(entrega);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Obtener entregas finalizadas por nit
    @GetMapping("/entregas-finalizadas")
    public ResponseEntity<List<GetMFRequerimientosEntregasProjection>> getEntregasByNIT(@RequestParam Integer nit) {
        try {
            List<GetMFRequerimientosEntregasProjection> entrega = repository.findEntregasByNIT(nit);
            return ResponseEntity.ok(entrega);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
