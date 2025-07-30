package com.mf.mf.controller.MFReporteSocietario;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.mf.dto.MFReporteSocietario.*;
import com.mf.mf.services.MFReporteSocietarioServices.MFReporteSocietarioServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reporte-societario")
@Tag(name = "Reporte Societario", description = "Reporte societario vigilado")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MFReporteSocietarioController {

    private final MFReporteSocietarioServices reporteSocietarioService;

    @PostMapping(value = "/saveReporte", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> recibirFormulario(
            @RequestPart("form") String formJson,
            @RequestPart("causales") String causalesJson,
            @RequestPart("facultades") String facultadesJson,
            @RequestPart("revisoresFiscales") String revisoresJson,
            @RequestPart("organismosAdministracion") String organismosAdminJson,
            @RequestPart("reuniones") String reunionesJson,
            @RequestPart("nit") String nit,
            @RequestPart("idHeredado") String idHeredado
    ) {

        try {
            reporteSocietarioService.procesarFormulario(
                    formJson,
                    causalesJson,
                    facultadesJson,
                    revisoresJson,
                    organismosAdminJson,
                    reunionesJson,
                    nit,
                    idHeredado
            );

            // Aquí puedes pasar el resto de los campos también al servicio si lo deseas.
            return ResponseEntity.ok("Datos recibidos y procesados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el formulario: " + e.getMessage());
        }
    }


    //LISTAR INFO ESPECIFICA SEGUN IDHeredado y NIT
    @GetMapping
    public ResponseEntity<MFReporteSocietarioWithRelationshipDTO> obtenerPorNitYHeredado(
            @RequestParam Integer nit,
            @RequestParam Integer idHeredado) {
        try {
            System.out.println(reporteSocietarioService
                    .obtenerPorNitYHeredado(nit, idHeredado));
            MFReporteSocietarioWithRelationshipDTO dto = reporteSocietarioService
                    .obtenerPorNitYHeredado(nit, idHeredado);
            System.out.println(dto);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            log.error("e1: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
