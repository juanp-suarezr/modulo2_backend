package com.mf.mf.model.excel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFEstadoResultados\"")
public class MFEstadoResultados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer annio;
    private Boolean estado;
    private Integer resultadoIntegralPropietarios;
    private Integer resultadoIntegralNoControladoras;
    private Integer resultadoIntegralTotal;
    private String validacionEstadoResultados;
    private Integer ingresosActividadesOrdinarias;
    private Integer ingresosActividadesTransporte;
    private Integer ingresosOtrasActividades;
    private Integer costoVentas;
    private Integer amortizacion;
    private Integer depreciacion;
    private Integer gananciaBruta;
    private Integer gastosVentas;
    private Integer gastosAdministracion;
    private Integer otrosGastosOperacionales;
    private Integer otrosIngresosOperacionales;
    private Integer otrasGananciasPerdidasOperacionales;
    private Integer gananciaOperacion;
    private Integer diferenciaDividendosActivos;
    private Integer gananciaPosicionMonetariaNeta;
    private Integer gananciasBajaActivosFinancieros;
    private Integer ingresosNoOperacionales;
    private Integer ingresosFinancieros;
    private Integer otrosIngresosNoOperacionales;
    private Integer gastosNoOperacionales;
    private Integer costosFinancieros;
    private Integer otrosGastosFinancieros;
    private Integer interesesDeuda;
    private Integer otrosGastosNoOperacionales;
    private Integer participacionGananciasAsociadas;
    private Integer gananciaAntesImpuestos;
    private Integer ingresoImpuestos;
    private Integer gastoImpuestoGanancias;
    private Integer gananciaOperacionesContinuadas;
    private Integer gananciaOperacionesDiscontinuadas;
    private Integer gananciaNeta;
    private Integer nit;
    private Boolean actual;
    private Integer idHeredado;
}
