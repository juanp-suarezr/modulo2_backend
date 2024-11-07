package com.mf.mf.controller.MFRequerimientoController;

import com.mf.mf.dto.MFHashDelegaturaDTO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.payload.ApiResponse;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientoProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFRequerimientosTableProjection;
import com.mf.mf.services.MFRequerimientoServices.MFRequerimientoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/requerimiento")
@Tag(name = "Entidad requerimiento", description = "Requerimiento")
@CrossOrigin(origins = "*")
public class MFRequerimientoController {

    @Autowired
    private MFRequerimientoServices requerimientoServices;


    @CrossOrigin(origins = "", allowedHeaders = "")
    @PostMapping()
    public ResponseEntity<ApiResponse<MFRequerimientoDTO>> create(@RequestBody @Valid MFRequerimientoDTO body) {
        MFRequerimientoDTO saved_item = requerimientoServices.save(body);
        ApiResponse<MFRequerimientoDTO> response = new ApiResponse<>("Registro almacenado exitosamente", saved_item);
        return ResponseEntity.ok(response);
    }

    //Obtener requerimientos tabla principal
    @CrossOrigin(origins = "", allowedHeaders = "")
    @GetMapping
    public List<GetMFRequerimientosTableProjection> getRequerimientos() {
        return requerimientoServices.obtenerRequerimientos();
    }

    //get detalles completos
    @CrossOrigin(origins = "", allowedHeaders = "")
    @GetMapping("/by-id/{idRequerimiento}")
    public GetMFRequerimientoProjection getRequerimientoID(@PathVariable Long idRequerimiento) {
        return requerimientoServices.obtenerRequerimientoByID(idRequerimiento);
    }


    @DeleteMapping("/{idRequerimiento}")
    public ResponseEntity<?> deleteRequerimiento(@PathVariable Long idRequerimiento) {
        requerimientoServices.deleteRequerimiento(idRequerimiento);
        return ResponseEntity.ok(idRequerimiento);
    }

    @CrossOrigin(origins = "", allowedHeaders = "")
    @PutMapping("/{idRequerimiento}")
    public ResponseEntity<ApiResponse<MFRequerimientoDTO>> updateRequerimientos(@PathVariable Long idRequerimiento, @RequestBody @Valid MFRequerimientoDTO dto) {
        MFRequerimientoDTO updateRequerimiento = requerimientoServices.updateRequerimiento(idRequerimiento, dto);
        ApiResponse<MFRequerimientoDTO> response = new ApiResponse<>("Registro Actualizado exitosamente", updateRequerimiento);
        return ResponseEntity.ok(response);
    }

}
