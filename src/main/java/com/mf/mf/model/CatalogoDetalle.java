package com.mf.mf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "datosmaestros", name = "catalogodetalle")
public class CatalogoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idpadre", insertable = false, updatable = false)
    private Integer idPadre;  // Clave foránea

    private String descripcion;
    private String detalle;
    private  Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idpadre", nullable = false)
    @JsonBackReference
    private Catalogo catalogo;
}

