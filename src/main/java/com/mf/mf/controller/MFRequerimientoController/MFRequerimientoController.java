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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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


    @DeleteMapping("/{idRequerimiento}")
    public ResponseEntity<?> deleteRequerimiento(@PathVariable Long idRequerimiento) {
        requerimientoServices.deleteRequerimiento(idRequerimiento);
        return ResponseEntity.ok(idRequerimiento);
    }

    @PutMapping("/{idRequerimiento}")
    public ResponseEntity<ApiResponse<MFRequerimientoDTO>> updateRequerimientos(@PathVariable Long idRequerimiento, @RequestBody @Valid MFRequerimientoDTO dto) {
        MFRequerimientoDTO updateRequerimiento = requerimientoServices.updateRequerimiento(idRequerimiento, dto);
        ApiResponse<MFRequerimientoDTO> response = new ApiResponse<>("Registro Actualizado exitosamente", updateRequerimiento);
        return ResponseEntity.ok(response);
    }

}
