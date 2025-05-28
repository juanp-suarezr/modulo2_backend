package com.mf.mf.dto.MFReporteSocietario;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MFConvocantesDTO {

    private String tipoDocumento;

    @Column(length = 10)
    private String nroIdentificacion;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 100)
    private String rol;

}
