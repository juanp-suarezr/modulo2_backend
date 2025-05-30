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
    private Long annio;
    private Boolean estado;
    private Long resultadoIntegralPropietarios;
    private Long resultadoIntegralNoControladoras;
    private Long resultadoIntegralTotal;
    private String validacionEstadoResultados;
    private Long ingresosActividadesOrdinarias;
    private Long ingresosActividadesTransporte;
    private Long ingresosOtrasActividades;
    private Long costoVentas;
    private Long amortizacion;
    private Long depreciacion;
    private Long gananciaBruta;
    private Long gastosVentas;
    private Long gastosAdministracion;
    private Long otrosGastosOperacionales;
    private Long otrosIngresosOperacionales;
    private Long otrasGananciasPerdidasOperacionales;
    private Long gananciaOperacion;
    private Long diferenciaDividendosActivos;
    private Long gananciaPosicionMonetariaNeta;
    private Long gananciasBajaActivosFinancieros;
    private Long ingresosNoOperacionales;
    private Long ingresosFinancieros;
    private Long otrosIngresosNoOperacionales;
    private Long gastosNoOperacionales;
    private Long costosFinancieros;
    private Long otrosGastosFinancieros;
    private Long interesesDeuda;
    private Long otrosGastosNoOperacionales;
    private Long participacionGananciasAsociadas;
    private Long gananciaAntesImpuestos;
    private Long ingresoImpuestos;
    private Long gastoImpuestoGanancias;
    private Long gananciaOperacionesContinuadas;
    private Long gananciaOperacionesDiscontinuadas;
    private Long gananciaNeta;
    private Integer nit;
    private Boolean actual;
    private Integer idHeredado;
}
