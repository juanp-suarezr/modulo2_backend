package com.mf.mf.controller.MFHeredadosController;

import com.mf.mf.dto.MFHashHeredadoDTO;
import com.mf.mf.dto.MFRequerimientoDTO;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.payload.ApiResponse;
import com.mf.mf.services.MFRequerimientoServices.MFHeredadosServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/heredados")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Entidad heredados", description = "Heredados")
public class MFHeredadosController {

    private final MFHeredadosServices mfHeredadosServices;

    public MFHeredadosController(MFHeredadosServices mfHeredadosServices) {
        this.mfHeredadosServices = mfHeredadosServices;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<MFHashHeredadoDTO>>> crearRegistros(
            @RequestParam Long idProgramacion,
            @RequestParam List<Integer> idVigilados,
            @RequestParam String fechaFin,
            @RequestParam Integer estado) {

//        List<MFHashHeredadoDTO> registrosCreados = mfHeredadosServices.crearRegistros(idProgramacion, idVigilados, LocalDate.parse(fechaFin), estado);
        List<MFHashHeredadoDTO> registrosCreados = List.of();
        ApiResponse<List<MFHashHeredadoDTO>> response = new ApiResponse<>("Registro almacenado exitosamente", registrosCreados);
        return ResponseEntity.ok(response);
    }

}
