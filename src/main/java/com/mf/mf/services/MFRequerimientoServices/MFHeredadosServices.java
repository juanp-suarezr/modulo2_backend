package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFHashDigitoNITDTO;
import com.mf.mf.dto.MFHashHeredadoDTO;
import com.mf.mf.dto.MFVigiladoDTO;
import com.mf.mf.model.MFHashDelegatura;
import com.mf.mf.model.MFHashDigitoNIT;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.projection.GetMFHashDigitoNITMUVProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDelegaturaProjection;
import com.mf.mf.projection.MFRequerimientoProjection.GetMFHashDigitoNITProjection;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDelegaturaRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDigitoNITRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class MFHeredadosServices {
    @Autowired
    private final MFHeredadosRepository MFHeredadosRepository;

    @Autowired
    private final MFHashDelegaturaRepository MFHashDelegaturaRepository;

    @Autowired
    private final MFHashDigitoNITRepository mfHashDigitoNITRepository;


    public MFHeredadosServices(MFHeredadosRepository mfHeredadosRepository, MFHashDelegaturaRepository mfHashDelegaturaRepository, MFHashDigitoNITRepository mfHashDigitoNITRepository) {
        this.MFHeredadosRepository = mfHeredadosRepository;
        this.MFHashDelegaturaRepository = mfHashDelegaturaRepository;
        this.mfHashDigitoNITRepository = mfHashDigitoNITRepository;

    }

    @Transactional
    public List<MFHashHeredadoDTO> crearRegistros(
            long idProgramacion,
            List<MFVigiladoDTO> vigilados,
            LocalDate fechaFin,
            Integer estado,
            Boolean isIndividual,
            Integer tipoProgamacion) {

        if (vigilados == null || vigilados.isEmpty()) {
            throw new IllegalArgumentException("La lista de vigilados no puede estar vacía.");
        }

        List<MFHashHeredado> registrosCreados = new ArrayList<>();
        System.out.print(estado);
        for (MFVigiladoDTO vigilado : vigilados) {
            if (!MFHeredadosRepository.existsByIdProgramacionAndIdVigilado(idProgramacion, vigilado.getIdVigilado())) {
                MFHashHeredado registro = new MFHashHeredado();
                registro.setIdProgramacion(idProgramacion);
                registro.setIdVigilado(vigilado.getIdVigilado());
                registro.setNit(vigilado.getNit());
                registro.setFechaEntrega(fechaFin);
                registro.setEstadoEntrega(estado);
                registro.setEstado(true);
                registro.setIndividual(isIndividual);
                registro.setTipoProgramacion(tipoProgamacion);

                registrosCreados.add(MFHeredadosRepository.save(registro));
            }
        }

        return registrosCreados.stream()
                .map(registro -> new MFHashHeredadoDTO(
                        registro.getIdHeredado(),
                        registro.getIdVigilado(),
                        registro.getNit(),
                        registro.getFechaEntrega(),
                        registro.getEstadoEntrega(),
                        registro.isIndividual()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MFHashHeredadoDTO> crearRegistroMUV(
            long idVigilado,
            String nit,
            long tipoVigilado) {


        List<MFHashHeredado> registrosCreados = new ArrayList<>();

        // Buscar programaciones que coincidan  el tipo de vigilado
        List<GetMFHashDelegaturaProjection> programacionesDelegatura = MFHashDelegaturaRepository.findByIdTipoVigilado(tipoVigilado);


        for (GetMFHashDelegaturaProjection programacion : programacionesDelegatura) {
            MFHashHeredado registro = new MFHashHeredado();
            registro.setIdProgramacion(programacion.getIdProgramacion());
            registro.setIdVigilado((int) idVigilado);
            registro.setNit(Integer.valueOf(nit));
            registro.setFechaEntrega(programacion.getFechaFin());
            registro.setEstadoEntrega(285);
            registro.setEstado(true);
            registro.setIndividual(false);
            registro.setTipoProgramacion(232);

            registrosCreados.add(MFHeredadosRepository.save(registro));


        }


//        String nitComoString = String.valueOf(nit); // Convertimos el NIT a String
        String lastOneDigits = nit.substring(nit.length() - 1);
        String lastTwoDigits = nit.substring(nit.length() - 2);
        String lastThreeDigits = nit.substring(nit.length() - 3);

        // Search for records matching the NIT's last digits
        // Consultas utilizando las projections
        List<GetMFHashDigitoNITMUVProjection> resultadosUnicos = mfHashDigitoNITRepository.findByNITunico(lastOneDigits);

        resultadosUnicos.addAll(mfHashDigitoNITRepository.findByNITultimosDIgitos(256, lastTwoDigits));

        resultadosUnicos.addAll(mfHashDigitoNITRepository.findByNITultimos3DIgitos(257, lastThreeDigits));

        for (GetMFHashDigitoNITMUVProjection programacion : resultadosUnicos) {

            MFHashHeredado registro = new MFHashHeredado();
            registro.setIdProgramacion(programacion.getIdProgramacion());
            registro.setIdVigilado((int) idVigilado);
            registro.setNit(Integer.valueOf(nit));
            registro.setFechaEntrega(programacion.getFechaFin());
            registro.setEstadoEntrega(285);
            registro.setEstado(true);
            registro.setIndividual(false);
            registro.setTipoProgramacion(234);

            registrosCreados.add(MFHeredadosRepository.save(registro));


        }

        // Mapear los registros creados a DTO
        return registrosCreados.stream()
                .map(registro -> new MFHashHeredadoDTO(
                        registro.getIdHeredado(),
                        registro.getIdVigilado(),
                        registro.getNit(),
                        registro.getFechaEntrega(),
                        registro.getEstadoEntrega(),
                        registro.isIndividual()
                ))
                .collect(Collectors.toList());

    }



}
