package com.mf.mf.dto.MFIndicadoresFinancieros;

import com.mf.mf.dto.MFHashDelegaturaDTO;
import com.mf.mf.dto.MFHashDigitoNITDTO;
import com.mf.mf.dto.MFVigiladoDTO;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//USAR SIN HASH
@Data
public class MFIndicadoresFinancierosDTO {

    private Long activoCorriente;
    private Long pasivoCorriente;
    private Long capitalTrabajo;
    private double liquidez;
    private double solvencia;
    private double rentaNeta;
    private double rentaOperacional;


    public MFIndicadoresFinancierosDTO(Long activoCorriente, Long pasivoCorriente, Long capitalTrabajo,
                                       double liquidez, double solvencia, double rentaNeta, double rentaOperacional) {
        this.activoCorriente = activoCorriente;
        this.pasivoCorriente = pasivoCorriente;
        this.capitalTrabajo = capitalTrabajo;
        this.liquidez = liquidez;
        this.solvencia = solvencia;
        this.rentaNeta = rentaNeta;
        this.rentaOperacional = rentaOperacional;
    }
}
