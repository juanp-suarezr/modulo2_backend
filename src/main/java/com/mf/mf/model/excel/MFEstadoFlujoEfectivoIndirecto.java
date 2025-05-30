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
    private Long gananciaPerdida;
    private Long ajustesGastosImpuestosGanancias;
    private Long ajustesGastosDepreciacionAmortizacion;
    private Long ajustesDeterioroReversionPerdidas;
    private Long ajustesProvisiones;
    private Long ajustesCostosFinancieros;
    private Long ajustesPerdidasGananciasMonedaExtranjera;
    private Long ajustesPerdidasGananciasValorRazonable;
    private Long ajustesGananciasNoDistribuidasAsociadas;
    private Long ajustesPerdidasGananciasDisposicionActivosNoCorrientes;
    private Long otrosAjustesConciliarGananciaPerdida;
    private Long totalAjustesConciliarGananciaPerdida;
    private Long ajustesDisminucionesIncrementosInventarios;
    private Long ajustesDisminucionIncrementoCuentasPorCobrar;
    private Long ajustesDisminucionesIncrementosOtrasCuentasCobrar;
    private Long ajustesIncrementoDisminucionCuentasPorPagar;
    private Long ajustesIncrementosDisminucionesOtrasCuentasPagar;
    private Long otrasEntradasSalidasEfectivo;
    private Long flujosEfectivoNetosActividadesOperacion;
    private Long flujosEfectivoPerdidaControlSubsidiarias;
    private Long flujosEfectivoObtenerControlSubsidiarias;
    private Long cobrosVentaPatrimonioInstrumentosDeuda;
    private Long pagosAdquirirPatrimonioInstrumentosDeuda;
    private Long cobrosVentaParticipacionesNegociosConjuntos;
    private Long pagosAdquirirParticipacionesNegociosConjuntos;
    private Long ventaPropiedadesPlantaEquipo;
    private Long comprasPropiedadesPlantaEquipo;
    private Long ventaActivosIntangibles;
    private Long comprasActivosIntangibles;
    private Long recursosVentasOtrosActivosLargoPlazo;
    private Long comprasOtrosActivosLargoPlazo;
    private Long anticiposPrestamosConcedidosTerceros;
    private Long cobrosReembolsoAnticiposPrestamos;
    private Long pagosContratosDerivados;
    private Long cobrosContratosDerivados;
    private Long dividendosRecibidos;
    private Long interesesRecibidos;
    private Long interesesPagados;
    private Long flujosEfectivoNetosActividadesInversion;
    private Long aumentosCapitalRecolocacionAcciones;
    private Long disminucionCapitalReadquisicionAcciones;
    private Long pagosOtrasParticipacionesPatrimonio;
    private Long aumentoPrimaEmision;
    private Long disminucionPrimaEmision;
    private Long importesProcedentesPrestamos;
    private Long reembolsosPrestamos;
    private Long pagosPasivosArrendamientosFinancieros;
    private Long dividendosPagados;
    private Long flujosEfectivoNetosActividadesFinanciacion;
    private Long incrementoDisminucionEfectivoEquivalentes;
    private Long efectosTasaCambioEfectivoEquivalentes;
    private Long efectivoEquivalentesPrincipioPeriodo;
    private Long efectivoEquivalentesFinalPeriodo;
    private Integer nit;
    private Integer annio;
    private Integer idHeredado;
    private Boolean actual;
    
}
