package com.mf.mf.services.MFRequerimientoServices;

import com.mf.mf.dto.MFHashHeredadoDTO;
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
    public List<MFHashHeredadoDTO> crearRegistros
            (long idProgramacion, // Puede ser MFHashDigitoNIT o MFHashDelegaturas
             List<Integer> idVigilados,
             String fechaFin, Integer estado) {
        // Validar la entrada
        if (idVigilados == null || idVigilados.isEmpty()) {
            throw new IllegalArgumentException("Los datos proporcionados son inválidos.");
        }
        List<MFHashHeredado> registrosCreados = new ArrayList<>();


        for (Integer idVigilado : idVigilados) {
            // Verificar si ya existe
            if (!MFHeredadosRepository.existsByIdProgramacionAndIdVigilado(idProgramacion, idVigilado)) {
                MFHashHeredado registro = new MFHashHeredado();
                registro.setIdProgramacion(idProgramacion);
                registro.setIdVigilado(idVigilado);
                registro.setFechaEntrega(LocalDate.parse(fechaFin));
                registro.setEstadoEntrega(estado);
                registro.setEstado(true);
                // Establecer otros campos comunes

                registrosCreados.add(MFHeredadosRepository.save(registro));
            }
        }

        return registrosCreados.stream()
                .map(registro -> new MFHashHeredadoDTO(
                        registro.getIdHeredado(),
                        registro.getIdVigilado(),
                        registro.getFechaEntrega(),
                        registro.getEstadoEntrega()
                ))
                .collect(Collectors.toList());
    }


}
