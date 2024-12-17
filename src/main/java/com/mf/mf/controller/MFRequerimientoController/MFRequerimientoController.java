package com.mf.mf.controller.MFRequerimientoController;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.dto.MFRequerimientoWithHashDTO;
import com.mf.mf.payload.ApiResponse;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosTableProjection;
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
@Tag(name = "Entidad requerimiento", description = "Requerimiento")
@CrossOrigin(origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MFRequerimientoController {

    @Autowired
    private MFRequerimientoServices requerimientoServices;


    @PostMapping()
    public ResponseEntity<ApiResponse<MFRequerimientoDTO>> create(@RequestBody @Valid MFRequerimientoDTO body) {

        MFRequerimientoDTO saved_item = requerimientoServices.save(body);
        ApiResponse<MFRequerimientoDTO> response = new ApiResponse<>("Registro almacenado exitosamente", saved_item);
        return ResponseEntity.ok(response);
    }

    //Obtener requerimientos tabla principal

    @GetMapping
    public List<GetMFRequerimientosTableProjection> getRequerimientos() {
        return requerimientoServices.obtenerRequerimientos();
    }

    //    get detalles completos
    @GetMapping("/by-id/{idRequerimiento}")
    public ResponseEntity<MFRequerimientoWithHashDTO> getRequerimientoById(@PathVariable Long idRequerimiento) {
        try {
            MFRequerimientoWithHashDTO requerimientoDTO = requerimientoServices.obtenerRequerimientoByID(idRequerimiento);
            return ResponseEntity.ok(requerimientoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Autowired
    public MFRequerimientoController(MFRequerimientoServices requerimientoServices) {
        this.requerimientoServices = requerimientoServices;
    }


    @PutMapping("/anular/{idRequerimiento}")
    public ResponseEntity<MFRequerimientoDTO> anularRequerimiento(
            @PathVariable Long idRequerimiento) {
        MFRequerimientoDTO updatedRequerimiento = requerimientoServices.AnularRequerimiento(idRequerimiento);
        return ResponseEntity.ok(updatedRequerimiento);
    }

}
