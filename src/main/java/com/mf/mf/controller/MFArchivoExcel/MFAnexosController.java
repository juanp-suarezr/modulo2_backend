package com.mf.mf.controller.MFArchivoExcel;

import com.mf.mf.dto.excel.MFAnexosDTO;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.excel.MFAnexos;
import com.mf.mf.projection.MFExcelProjection.GetMFAnexosProjection;
import com.mf.mf.repository.MFExcelRepository.MFAnexosRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/anexos")
@Tag(name = "Cargue de archivos anexos", description = "Cargue de campos de anexos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MFAnexosController {

    private final MFAnexosRepository mfAnexosRepository;
    private final MFHeredadosRepository mfHeredadosRepository;

    // Obtener anexos por idHeredado
    @GetMapping("/byIDHeredado")
    public ResponseEntity<List<GetMFAnexosProjection>> findByidHeredado(@RequestParam String idHeredado) {
        try {
            List<GetMFAnexosProjection> anexos = mfAnexosRepository.findAnexosByHeredado(Integer.valueOf(idHeredado));
            return ResponseEntity.ok(anexos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Obtener historial -- rol misional --
    @GetMapping("/byIDHeredadoComparativo")
    public ResponseEntity<List<GetMFAnexosProjection>> findByidHeredadoComparativo(@RequestParam String idHeredado) {
        try {
            List<GetMFAnexosProjection> anexos = mfAnexosRepository.findAnexosByHeredadoComparativo(Integer.valueOf(idHeredado));
            return ResponseEntity.ok(anexos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/guardar")
    @Transactional
    public ResponseEntity<?> guardarAnexo(@RequestBody MFAnexosDTO requestBody) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<MFHashHeredado> heredadoOpt = mfHeredadosRepository.findByIdHeredado(requestBody.getIdHeredado());
            if (heredadoOpt.isEmpty()) {
                response.put("error", "No se encontró un heredado con el id proporcionado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            MFHashHeredado heredado = heredadoOpt.get();
            List<MFAnexos> anexoExistente = mfAnexosRepository.findAnexosByHeredadoList(requestBody.getIdHeredado());
            System.out.println(anexoExistente);

            System.out.println(anexoExistente.isEmpty());
            MFAnexos anexo;
            if (anexoExistente.isEmpty()) {
                System.out.println("anexo create");
                anexo = new MFAnexos();
                anexo.setIdHeredado(requestBody.getIdHeredado());
            } else {
                anexo = anexoExistente.get(0);
                System.out.println("anexo update --> " + anexo);
            }
            System.out.println("anexo --> " + anexo);


            anexo.setCaratula(requestBody.getCaratula());
            anexo.setEstadoSituacionFinanciera(requestBody.getEstadoSituacionFinanciera());
            anexo.setEstadoResultados(requestBody.getEstadoResultados());
            anexo.setEstadoResultadosIntegral(requestBody.getEstadoResultadosIntegral());
            anexo.setFlujoEfectivo(requestBody.getFlujoEfectivo());
            anexo.setEstadoCambiosPatrimonio(requestBody.getEstadoCambiosPatrimonio());
            anexo.setDictamenFiscal(requestBody.getDictamenFiscal());
            anexo.setRevelacionesEstadosFinancieros(requestBody.getRevelacionesEstadosFinancieros());
            anexo.setNotasEstadosFinancieros(requestBody.getNotasEstadosFinancieros());
            anexo.setCertificacionCumplimientoEEFF(requestBody.getCertificacionCumplimientoEEFF());
            anexo.setPoliticasContables(requestBody.getPoliticasContables());
            anexo.setInformeGestion(requestBody.getInformeGestion());
            anexo.setProyectoDistribucionUtilidadesEmpresas(requestBody.getProyectoDistribucionUtilidadesEmpresas());
            anexo.setDeclaracionRenta(requestBody.getDeclaracionRenta());
            anexo.setComposicionAccionaria(requestBody.getComposicionAccionaria());
            anexo.setActaAsambleaAprobacionEF(requestBody.getActaAsambleaAprobacionEF());
            anexo.setFechaEntrega(LocalDate.now());
            anexo.setRelacionLitigios(requestBody.getRelacionLitigios());
            anexo.setActoAdministrativo(requestBody.getActoAdministrativo());
            anexo.setRelacionCuentasXPagarPasivo(requestBody.getRelacionCuentasXPagarPasivo());
            anexo.setRelacionTipoEquipo(requestBody.getRelacionTipoEquipo());
            anexo.setPlanInversiones(requestBody.getPlanInversiones());
            anexo.setCronogramaCumplimientoInversiones(requestBody.getCronogramaCumplimientoInversiones());
            anexo.setCronogramaMantenimientoEquipos(requestBody.getCronogramaMantenimientoEquipos());
            anexo.setMatrizRiesgos(requestBody.getMatrizRiesgos());
            anexo.setPlanMejoramiento(requestBody.getPlanMejoramiento());
            anexo.setContratos(requestBody.getContratos());
            anexo.setAcuerdoMunicipal(requestBody.getAcuerdoMunicipal());
            anexo.setResolucionPermisosOperecacion(requestBody.getResolucionPermisosOperecacion());
            anexo.setOtros(requestBody.getOtros());
            anexo.setEstado(true);

            if (anexoExistente.isEmpty()) {


                int estado = (heredado.getEstadoEntrega() == 286) ? 460 : 289;
                mfHeredadosRepository.actualizarEstadoEntrega(requestBody.getIdHeredado(), estado);


            }
            mfAnexosRepository.save(anexo);



            response.put("mensaje", "Anexo guardado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al procesar el archivo: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/guardarActualizado")
    @Transactional
    public ResponseEntity<?> guardarAnexo1(@RequestBody MFAnexosDTO requestBody) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<MFHashHeredado> heredadoOpt = mfHeredadosRepository.findByIdHeredado(requestBody.getIdHeredado());
            if (heredadoOpt.isEmpty()) {
                response.put("error", "No se encontró un heredado con el id proporcionado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            MFHashHeredado heredado = heredadoOpt.get();
            List<MFAnexos> anexoExistente = mfAnexosRepository.findAnexosByHeredadoList(requestBody.getIdHeredado());
            System.out.println(anexoExistente);

            System.out.println(anexoExistente.isEmpty());
            MFAnexos anexo;

            mfAnexosRepository.changeEstado(requestBody.getIdHeredado());

            anexo = new MFAnexos();
            anexo.setIdHeredado(requestBody.getIdHeredado());



            anexo.setCaratula(requestBody.getCaratula());
            anexo.setEstadoSituacionFinanciera(requestBody.getEstadoSituacionFinanciera());
            anexo.setEstadoResultados(requestBody.getEstadoResultados());
            anexo.setEstadoResultadosIntegral(requestBody.getEstadoResultadosIntegral());
            anexo.setFlujoEfectivo(requestBody.getFlujoEfectivo());
            anexo.setEstadoCambiosPatrimonio(requestBody.getEstadoCambiosPatrimonio());
            anexo.setDictamenFiscal(requestBody.getDictamenFiscal());
            anexo.setRevelacionesEstadosFinancieros(requestBody.getRevelacionesEstadosFinancieros());
            anexo.setNotasEstadosFinancieros(requestBody.getNotasEstadosFinancieros());
            anexo.setCertificacionCumplimientoEEFF(requestBody.getCertificacionCumplimientoEEFF());
            anexo.setPoliticasContables(requestBody.getPoliticasContables());
            anexo.setInformeGestion(requestBody.getInformeGestion());
            anexo.setProyectoDistribucionUtilidadesEmpresas(requestBody.getProyectoDistribucionUtilidadesEmpresas());
            anexo.setDeclaracionRenta(requestBody.getDeclaracionRenta());
            anexo.setComposicionAccionaria(requestBody.getComposicionAccionaria());
            anexo.setActaAsambleaAprobacionEF(requestBody.getActaAsambleaAprobacionEF());
            anexo.setFechaEntrega(LocalDate.now());
            anexo.setRelacionLitigios(requestBody.getRelacionLitigios());
            anexo.setActoAdministrativo(requestBody.getActoAdministrativo());
            anexo.setRelacionCuentasXPagarPasivo(requestBody.getRelacionCuentasXPagarPasivo());
            anexo.setRelacionTipoEquipo(requestBody.getRelacionTipoEquipo());
            anexo.setPlanInversiones(requestBody.getPlanInversiones());
            anexo.setCronogramaCumplimientoInversiones(requestBody.getCronogramaCumplimientoInversiones());
            anexo.setCronogramaMantenimientoEquipos(requestBody.getCronogramaMantenimientoEquipos());
            anexo.setMatrizRiesgos(requestBody.getMatrizRiesgos());
            anexo.setPlanMejoramiento(requestBody.getPlanMejoramiento());
            anexo.setContratos(requestBody.getContratos());
            anexo.setAcuerdoMunicipal(requestBody.getAcuerdoMunicipal());
            anexo.setResolucionPermisosOperecacion(requestBody.getResolucionPermisosOperecacion());
            anexo.setOtros(requestBody.getOtros());
            anexo.setEstado(true);



            mfHeredadosRepository.actualizarEstadoEntrega(requestBody.getIdHeredado(), 480);
            mfAnexosRepository.save(anexo);



            response.put("mensaje", "Anexo guardado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al procesar el archivo: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
