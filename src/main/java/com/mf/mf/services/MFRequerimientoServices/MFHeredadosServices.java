package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFHashHeredadoDTO;
import com.mf.mf.dto.MFVigiladoDTO;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
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

    public MFHeredadosServices(MFHeredadosRepository mfHeredadosRepository) {
        this.MFHeredadosRepository = mfHeredadosRepository;
    }

    @Transactional
    public List<MFHashHeredadoDTO> crearRegistros(
            long idProgramacion,
            List<MFVigiladoDTO> vigilados,
            LocalDate fechaFin,
            Integer estado,
            Boolean isIndividual) {

        if (vigilados == null || vigilados.isEmpty()) {
            throw new IllegalArgumentException("La lista de vigilados no puede estar vacía.");
        }

        List<MFHashHeredado> registrosCreados = new ArrayList<>();

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



}
