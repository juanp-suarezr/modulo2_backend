package com.mf.mf.model.excel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "financiero", name = "\"MFEstadoFlujoEfectivoIndirecto\"")
public class MFEstadoFlujoEfectivoIndirecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;
    private Integer gananciaPerdida;
    private Integer ajustesGastosImpuestosGanancias;
    private Integer ajustesGastosDepreciacionAmortizacion;
    private Integer ajustesDeterioroReversionPerdidas;
    private Integer ajustesProvisiones;
    private Integer ajustesCostosFinancieros;
    private Integer ajustesPerdidasGananciasMonedaExtranjera;
    private Integer ajustesPerdidasGananciasValorRazonable;
    private Integer ajustesGananciasNoDistribuidasAsociadas;
    private Integer ajustesPerdidasGananciasDisposicionActivosNoCorrientes;
    private Integer otrosAjustesConciliarGananciaPerdida;
    private Integer totalAjustesConciliarGananciaPerdida;
    private Integer ajustesDisminucionesIncrementosInventarios;
    private Integer ajustesDisminucionIncrementoCuentasPorCobrar;
    private Integer ajustesDisminucionesIncrementosOtrasCuentasCobrar;
    private Integer ajustesIncrementoDisminucionCuentasPorPagar;
    private Integer ajustesIncrementosDisminucionesOtrasCuentasPagar;
    private Integer otrasEntradasSalidasEfectivo;
    private Integer flujosEfectivoNetosActividadesOperacion;
    private Integer flujosEfectivoPerdidaControlSubsidiarias;
    private Integer flujosEfectivoObtenerControlSubsidiarias;
    private Integer cobrosVentaPatrimonioInstrumentosDeuda;
    private Integer pagosAdquirirPatrimonioInstrumentosDeuda;
    private Integer cobrosVentaParticipacionesNegociosConjuntos;
    private Integer pagosAdquirirParticipacionesNegociosConjuntos;
    private Integer ventaPropiedadesPlantaEquipo;
    private Integer comprasPropiedadesPlantaEquipo;
    private Integer ventaActivosIntangibles;
    private Integer comprasActivosIntangibles;
    private Integer recursosVentasOtrosActivosLargoPlazo;
    private Integer comprasOtrosActivosLargoPlazo;
    private Integer anticiposPrestamosConcedidosTerceros;
    private Integer cobrosReembolsoAnticiposPrestamos;
    private Integer pagosContratosDerivados;
    private Integer cobrosContratosDerivados;
    private Integer dividendosRecibidos;
    private Integer interesesRecibidos;
    private Integer interesesPagados;
    private Integer flujosEfectivoNetosActividadesInversion;
    private Integer aumentosCapitalRecolocacionAcciones;
    private Integer disminucionCapitalReadquisicionAcciones;
    private Integer pagosOtrasParticipacionesPatrimonio;
    private Integer aumentoPrimaEmision;
    private Integer disminucionPrimaEmision;
    private Integer importesProcedentesPrestamos;
    private Integer reembolsosPrestamos;
    private Integer pagosPasivosArrendamientosFinancieros;
    private Integer dividendosPagados;
    private Integer flujosEfectivoNetosActividadesFinanciacion;
    private Integer incrementoDisminucionEfectivoEquivalentes;
    private Integer efectosTasaCambioEfectivoEquivalentes;
    private Integer efectivoEquivalentesPrincipioPeriodo;
    private Integer efectivoEquivalentesFinalPeriodo;
    private Integer nit;
    private Integer annio;
    private Boolean actual;
    
}
