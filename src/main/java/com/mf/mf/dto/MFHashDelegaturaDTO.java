package com.mf.mf.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MFHashDelegaturaDTO {

    private long idProgramacion;
    private Integer idDelegatura; // Lista desplegable datos maestros
    private Integer idTipoVigilado; // Lista desplegable datos maestros
    private long idRequerimiento; // FK
    private LocalDate fechaFin;
    private boolean estado;
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo

    private List<MFVigiladoDTO> vigilados; // Nueva lista con ID y NIT de los vigilados

}
