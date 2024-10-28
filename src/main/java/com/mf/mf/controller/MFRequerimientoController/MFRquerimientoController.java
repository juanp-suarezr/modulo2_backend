package com.mf.mf.controller.MFRequerimientoController;

import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.payload.ApiResponse;
import com.mf.mf.services.MFRequerimientoServices.MFRequerimientoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/requerimiento")
@Tag(name = "Entidad requerimiento", description = "Requerimiento")
@CrossOrigin(origins = "*")
public class MFRquerimientoController {

    @Autowired
    private MFRequerimientoServices requerimientoServices;
    @PostMapping()
    public ResponseEntity<ApiResponse<MFRequerimientoDTO>> create(@RequestBody @Valid MFRequerimientoDTO body) {
        MFRequerimientoDTO saved_item = requerimientoServices.save(body);
        ApiResponse<MFRequerimientoDTO> response = new ApiResponse<>("Registro almacenado exitosamente", saved_item);
        return ResponseEntity.ok(response);
    }

}
