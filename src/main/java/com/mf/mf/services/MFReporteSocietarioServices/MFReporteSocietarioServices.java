package com.mf.mf.services.MFReporteSocietarioServices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mf.mf.dto.MFReporteSocietario.*;

import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.model.MFReporteSocietario.*;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.repository.MFReporteSocietarioRepository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MFReporteSocietarioServices {

    private final ObjectMapper mapper;

    private final MFReporteSocietarioRepository reporteSocietarioRepository;
    private final MFCausalesDisolucionRepository causalesDisolucionRepository;
    private final MFDatosAdicionalesRepository datosAdicionalesRepository;
    private final MFFacultadesRepository facultadesRepository;
    private final MFDatosCapitalRepository datosCapitalRepository;
    private final MFRevisoresFiscalesRepository revisoresFiscalesRepository;
    private final MFOrganismosAdministracionRepository organismosAdministracionRepository;
    private final MFReunionesRepository reunionesRepository;
    private final MFConvocantesRepository convocantesRepository;
    private final MFAcasReunionRepository acasReunionRepository;

    private final MFHeredadosRepository mfHeredadosRepository;

    public MFReporteSocietarioServices(MFReporteSocietarioRepository reporteSocietarioRepository, MFCausalesDisolucionRepository causalesDisolucionRepository, MFDatosAdicionalesRepository datosAdicionalesRepository, MFFacultadesRepository facultadesRepository, MFDatosCapitalRepository datosCapitalRepository, MFRevisoresFiscalesRepository revisoresFiscalesRepository, MFOrganismosAdministracionRepository organismosAdministracionRepository, MFReunionesRepository reunionesRepository, MFConvocantesRepository convocantesRepository, MFAcasReunionRepository acasReunionRepository, MFHeredadosRepository mfHeredadosRepository) {
        this.reporteSocietarioRepository = reporteSocietarioRepository;
        this.causalesDisolucionRepository = causalesDisolucionRepository;
        this.datosAdicionalesRepository = datosAdicionalesRepository;
        this.facultadesRepository = facultadesRepository;
        this.datosCapitalRepository = datosCapitalRepository;
        this.revisoresFiscalesRepository = revisoresFiscalesRepository;
        this.organismosAdministracionRepository = organismosAdministracionRepository;
        this.reunionesRepository = reunionesRepository;
        this.convocantesRepository = convocantesRepository;
        this.acasReunionRepository = acasReunionRepository;
        this.mfHeredadosRepository = mfHeredadosRepository;

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Transactional
    public void procesarFormulario(
            String formJson,
            String causalesJson,
            String facultadesJson,
            String revisoresJson,
            String organismosAdminJson,
            String reunionesJson,
            String nit,
            String idHeredado
    ) throws Exception {

        Map<String, Object> form = mapper.readValue(formJson, new TypeReference<Map<String, Object>>() {});


        // Utilidades seguras para extraer valores
        String tipoSociedad = String.valueOf(form.get("tipo_sociedad"));
        String tipoDocumentoCreacion = String.valueOf(form.get("tipo_documento_creacion"));
        String ciudadNotariaId = String.valueOf(form.get("ciudad_notaria_id"));
        String notaria = String.valueOf(form.get("notaria"));

        LocalDate fechaDocumento = parseFecha(form.get("fecha_documento"));
        String registroMercantil = String.valueOf(form.get("registro_mercantil"));
        LocalDate fechaRegistroMercantil = parseFecha(form.get("fecha_registro_mercantil"));
        String ciudadCamaraComercio = String.valueOf(form.get("ciudad_camara_comercio"));
        String nombreCamaraComercio = String.valueOf(form.get("nombre_camara_comercio"));

        Boolean vigenciaIndefinida = "Sí".equals(String.valueOf(form.get("vigencia_indefinida")));
        LocalDate fechaVigencia = parseFecha(form.get("fecha_vigencia"));

        String distribucionUtilidades = String.valueOf(form.get("distribucion_utilidades"));
        String duracionSociedad = String.valueOf(form.get("duracion_sociedad"));
        LocalDate fechaConvocatoria = parseFecha(form.get("fecha_convocatoria"));
        String formaConvocatoria = String.valueOf(form.get("forma_convocatoria"));
        String organismosDecision = String.valueOf(form.get("organismos_decision"));

        BigDecimal valorCapital = parseBigDecimal(form.get("valor_capital"));
        BigDecimal capitalAutorizado = parseBigDecimal(form.get("capital_autorizado"));
        Integer nroAcciones = parseInteger(form.get("nro_acciones"));
        BigDecimal capitalSuscrito = parseBigDecimal(form.get("capital_suscrito"));
        LocalDate fechaPagoCapitalSuscrito = parseFecha(form.get("fecha_pago_capital_suscrito"));
        Integer nroCuotas = parseInteger(form.get("nro_cuotas"));
        String tipoAccion = String.valueOf(form.get("tipo_accion"));
        BigDecimal valorAccion = parseBigDecimal(form.get("valor_accion"));
        BigDecimal capitalPagado = parseBigDecimal(form.get("capital_pagado"));
        Integer cantidadAccionistas = parseInteger(form.get("cantidad_accionistas"));

        System.out.println("Formulario recibido para NIT: " + nit);
        System.out.println("form: " + form);

        // Parseo de listas con TypeReference explícito
        List<String> causales = mapper.readValue(causalesJson, new TypeReference<List<String>>() {});
        List<MFFacultadesDTO> facultades = mapper.readValue(facultadesJson, new TypeReference<List<MFFacultadesDTO>>() {});
        List<MFRevisoresFiscalesDTO> revisores = mapper.readValue(revisoresJson, new TypeReference<List<MFRevisoresFiscalesDTO>>() {});
        List<MFOrganismosAdministracionDTO> organismos = mapper.readValue(organismosAdminJson, new TypeReference<List<MFOrganismosAdministracionDTO>>() {});
        System.out.println("organismos: " + organismos);
        List<MFReunionesDTO> reuniones = mapper.readValue(reunionesJson, new TypeReference<List<MFReunionesDTO>>() {});
        System.out.println("reuniones: " + reuniones);
        // Procesamiento o debug


        //GUARDAR DATOS

        //1. reporte societario
        MFReporteSocietario entity = new MFReporteSocietario();
        entity.setTipoSociedad(tipoSociedad);
        entity.setTipoDocumentoCreacion(tipoDocumentoCreacion);
        entity.setCiudadNotariaId(ciudadNotariaId);
        entity.setNotaria(notaria);
        entity.setFechaDocumento(fechaDocumento);
        entity.setRegistroMercantil(registroMercantil);
        entity.setFechaRegistroMercantil(fechaRegistroMercantil);
        entity.setCiudadCamaraComercio(ciudadCamaraComercio);
        entity.setNombreCamaraComercio(nombreCamaraComercio);
        entity.setVigenciaIndefinida(vigenciaIndefinida);
        entity.setFechaVigencia(fechaVigencia);
        entity.setEstado(true);
        entity.setIdHeredado(Integer.valueOf(idHeredado));
        entity.setNit(Integer.valueOf(nit));
        System.out.println("form: " + entity);
        MFReporteSocietario reporte = reporteSocietarioRepository.save(entity);
        //Causales de disolucion
        for (String causa : causales) {
            MFCausalesDisolucion entityCausal = new MFCausalesDisolucion();
            entityCausal.setConstitucion(reporte);
            entityCausal.setDescripcion(causa);
            entityCausal.setIdHeredado(Integer.valueOf(idHeredado));
            entityCausal.setNit(Integer.valueOf(nit));
            causalesDisolucionRepository.save(entityCausal);
        }

        //2. DATOS ADICIONALES
        MFDatosAdicionales entityDatosAdicionales = new MFDatosAdicionales();
        entityDatosAdicionales.setConstitucion(reporte);
        entityDatosAdicionales.setDistribucion_utilidades(distribucionUtilidades);
        entityDatosAdicionales.setDuracion_sociedad(duracionSociedad);
        entityDatosAdicionales.setFecha_convocatoria(fechaConvocatoria);
        entityDatosAdicionales.setForma_convocatoria(formaConvocatoria);
        entityDatosAdicionales.setOrganos_decision(organismosDecision);
        entityDatosAdicionales.setIdHeredado(Integer.valueOf(idHeredado));
        entityDatosAdicionales.setNit(Integer.valueOf(nit));
        entityDatosAdicionales.setEstado(true);
        datosAdicionalesRepository.save(entityDatosAdicionales);

        //3. FACULTADES
        for (MFFacultadesDTO facultad : facultades) {
            MFFacultades entityFacultades= new MFFacultades();
            entityFacultades.setConstitucion(reporte);
            entityFacultades.setRol(facultad.getRol());
            entityFacultades.setDescripcion(facultad.getDescripcion());
            entityFacultades.setIdHeredado(Integer.valueOf(idHeredado));
            entityFacultades.setNit(Integer.valueOf(nit));
            entityFacultades.setEstado(true);
            facultadesRepository.save(entityFacultades);
        }
        //REVISORES FISCALES
        for (MFRevisoresFiscalesDTO revisorF : revisores) {
            MFRevisoresFiscales entityRevisores= new MFRevisoresFiscales();
            entityRevisores.setConstitucion(reporte);
            entityRevisores.setTipo_identificacion(revisorF.getTipo_identificacion());
            entityRevisores.setNro_identificacion(revisorF.getNro_identificacion());
            entityRevisores.setNombre(revisorF.getNombre());
            entityRevisores.setPrincipal(revisorF.getPrincipal());
            entityRevisores.setActivo(revisorF.getActivo());
            entityRevisores.setFecha_inscripcion(revisorF.getFecha_inscripcion());
            entityRevisores.setNro_tarjeta_profesional(revisorF.getTipo_identificacion());
            entityRevisores.setEstado(true);
            entityRevisores.setIdHeredado(Integer.valueOf(idHeredado));
            entityRevisores.setNit(Integer.valueOf(nit));
            entityRevisores.setEstado(true);
            revisoresFiscalesRepository.save(entityRevisores);
        }

        //4. ORGANISMOS ADMINISTRACION
        for (MFOrganismosAdministracionDTO orgAdmin : organismos) {
            MFOrganismosAdministracion entityOrganismos= new MFOrganismosAdministracion();
            entityOrganismos.setConstitucion(reporte);
            entityOrganismos.setTipo_identificacion(orgAdmin.getTipo_identificacion());
            entityOrganismos.setNro_identificacion(orgAdmin.getNro_identificacion());
            entityOrganismos.setNombres(orgAdmin.getNombres());
            entityOrganismos.setApellidos(orgAdmin.getApellidos());
            entityOrganismos.setTipo(orgAdmin.getTipo());
            entityOrganismos.setActivo(orgAdmin.getActivo());
            entityOrganismos.setFecha_inscripcion(orgAdmin.getFecha_inscripcion());
            entityOrganismos.setNro_tarjeta_profesional(orgAdmin.getTipo_identificacion());
            entityOrganismos.setCalidad(orgAdmin.getCalidad());
            entityOrganismos.setEstado(true);
            entityOrganismos.setIdHeredado(Integer.valueOf(idHeredado));
            entityOrganismos.setNit(Integer.valueOf(nit));
            entityOrganismos.setEstado(true);
            organismosAdministracionRepository.save(entityOrganismos);
        }

        //5. REUNIONES
        for (MFReunionesDTO reunion : reuniones) {
            MFReuniones entityReuniones= new MFReuniones();
            entityReuniones.setConstitucion(reporte);
            entityReuniones.setNro_reunion(reunion.getNro_reunion());
            entityReuniones.setFecha_reunion(reunion.getFecha_reunion());
            entityReuniones.setTipo_reunion(reunion.getTipo_reunion());
            entityReuniones.setFecha_convocatoria(reunion.getFecha_convocatoria());
            entityReuniones.setDireccion(reunion.getDireccion());
            entityReuniones.setMunicipio_id(reunion.getMunicipio_id());
            entityReuniones.setMedio_informacion(reunion.getMedio_informacion());
            entityReuniones.setFecha_antelacion(reunion.getFecha_antelacion());
            entityReuniones.setEs_asamblea_general(reunion.getEs_asamblea_general());
            entityReuniones.setObservaciones(reunion.getObservaciones());
            entityReuniones.setEstado(true);
            entityReuniones.setIdHeredado(Integer.valueOf(idHeredado));
            entityReuniones.setNit(Integer.valueOf(nit));
            entityReuniones.setEstado(true);
            MFReuniones reunionGuardada = reunionesRepository.save(entityReuniones);

            //ACTAS
            for (MFActasDTO actas : reunion.getActas()) {
                MFActasReunion entityActas= new MFActasReunion();
                entityActas.setReunion(reunionGuardada);
                entityActas.setNumeroActa(actas.getNumeroActa());
                entityActas.setFechaActa(actas.getFechaActa());
                entityActas.setFechaInscripcion(actas.getFechaInscripcion());
                entityActas.setNroReunion(actas.getNroReunion());
                entityActas.setTemasTratados(actas.getTemasTratados());
                entityActas.setReformaEstatutaria(actas.getReformaEstatutaria());
                entityActas.setEstado(true);
                entityActas.setNit(Integer.valueOf(nit));
                entityActas.setEstado(true);
                acasReunionRepository.save(entityActas);
            }
            //CONVOCANTES
            for (MFConvocantesDTO convocantes : reunion.getConvocantes()) {
                MFConvocantesReunion entityConvocantes= new MFConvocantesReunion();
                entityConvocantes.setReunion(reunionGuardada);
                entityConvocantes.setTipoDocumento(convocantes.getTipoDocumento());
                entityConvocantes.setNroIdentificacion(convocantes.getNroIdentificacion());
                entityConvocantes.setNombre(convocantes.getNombre());
                entityConvocantes.setApellido(convocantes.getApellido());
                entityConvocantes.setRol(convocantes.getRol());
                entityConvocantes.setEstado(true);
                entityConvocantes.setNit(Integer.valueOf(nit));
                entityConvocantes.setEstado(true);
                convocantesRepository.save(entityConvocantes);
            }

        }

        Optional<MFHashHeredado> heredadoOpt = mfHeredadosRepository.findByIdHeredado(Integer.valueOf(idHeredado));

        MFHashHeredado heredado = heredadoOpt.get();

        int estado = (heredado.getEstadoEntrega() == 286) ? 460 : 289;
        mfHeredadosRepository.actualizarEstadoEntrega(Integer.valueOf(idHeredado), estado);
        mfHeredadosRepository.actualizarCargoExcel(Integer.valueOf(idHeredado));




    }

    // Métodos para convertir datos
    private LocalDate parseFecha(Object value) {
        if (value == null || value.toString().isBlank()) return null;

        String dateStr = value.toString();
        String[] patrones = { "yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy" }; // agrega más si lo necesitas

        for (String pattern : patrones) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateStr, formatter);
            } catch (Exception ignored) {}
        }

        System.err.println("Error parseando fecha: " + dateStr + " → No coincide con formatos esperados");
        return null;
    }

    private BigDecimal parseBigDecimal(Object value) {
        if (value == null || value.toString().isBlank()) return null;
        try {
            return new BigDecimal(value.toString());
        } catch (Exception e) {
            System.err.println("Error parseando BigDecimal: " + value + " → " + e.getMessage());
            return null;
        }
    }

    private Integer parseInteger(Object value) {
        if (value == null || value.toString().isBlank()) return null;
        try {
            return Integer.parseInt(value.toString());
        } catch (Exception e) {
            System.err.println("Error parseando Integer: " + value + " → " + e.getMessage());
            return null;
        }
    }

}
