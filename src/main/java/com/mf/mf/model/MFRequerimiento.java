package com.mf.mf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(schema = "financiero", name = "MFRequerimiento")
public class MFRequerimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRequerimiento;
    private Integer nombreRequerimiento; // Lista desplegable datos maestros
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaCreacion;
    private Integer periodoEntrega; // Lista desplegable datos maestros
    private Integer tipoProgramacion; // Lista desplegable datos maestros
    private Integer actoAdministrativo; // Input number
    private LocalDate fechaPublicacion;
    private Integer annioVigencia; // Input number
    private byte[] documentoActo;
    private Integer estadoVigilado; // Lista desplegable datos maestros
    private Integer estadoRequerimiento; // datos maestros asignacion por debajo
    private boolean estado;



    @JsonIgnore
    @OneToMany(mappedBy = "idRequerimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MFHashDelegatura> delegatura;

    @ManyToOne
    @JoinColumn(name = "\"nombreRequerimiento\"", referencedColumnName = "id", insertable = false, updatable = false)
    private CatalogoDetalle tipoRequerimientoDescripcion;

}
