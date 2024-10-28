package com.mf.mf.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "datosmaestros", name = "delegatura")


public class MFDelegatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
}
