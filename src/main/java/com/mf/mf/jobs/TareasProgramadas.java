package com.mf.mf.jobs;

import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDelegaturaRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFHashDigitoNITRepository;
import com.mf.mf.repository.MFRequerimientoRepository.MFRequerimientoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class TareasProgramadas {

    private final MFHeredadosRepository mFHeredadosRepository;
    private final MFRequerimientoRepository mfRequerimientoRepository;
    private final MFHashDelegaturaRepository mfHashDelegaturaRepository;
    private final MFHashDigitoNITRepository mfHashDigitoNITRepository;

    public TareasProgramadas(
            MFHeredadosRepository mFHeredadosRepository,
            MFRequerimientoRepository mfRequerimientoRepository,
            MFHashDelegaturaRepository mfHashDelegaturaRepository,
            MFHashDigitoNITRepository mfHashDigitoNITRepository
    ) {
        this.mFHeredadosRepository = mFHeredadosRepository;
        this.mfRequerimientoRepository = mfRequerimientoRepository;
        this.mfHashDelegaturaRepository = mfHashDelegaturaRepository;
        this.mfHashDigitoNITRepository = mfHashDigitoNITRepository;
    }

    // Tarea que se ejecuta cada 5 segundos
    @Scheduled(fixedRate = 5000)
    public void tareaCadaCincoSegundos() {

    }


    // Tarea programada usando una expresión cron
    @Scheduled(cron = "0 0 * * * *") // Cada hora en el minuto 0
    public void tareaConCron() {
        System.out.println("Tarea programada con cron (cada hora): " + System.currentTimeMillis());
    }

    // Tarea programada para ejecutarse todos los días a medianoche
    @Scheduled(cron = "0 0 0 * * *") // Cron para las 00:00 todos los días
    @Transactional
    public void actualizarEstados() {
        Integer estadoNuevo = 286;
        Integer estadoReq = 290;
        LocalDate fechaActual = LocalDate.now();

        mfRequerimientoRepository.actualizarEstadoPorFechaEntrega(estadoReq, fechaActual);
        mfHashDelegaturaRepository.actualizarEstadoPorFechaEntrega(estadoReq, fechaActual);
        mfHashDigitoNITRepository.actualizarEstadoPorFechaEntrega(estadoReq, fechaActual);
        mFHeredadosRepository.actualizarEstadoPorFechaEntrega(estadoNuevo, fechaActual);

        System.out.println("Actualización completada: Registros actualizados a estado " + estadoNuevo + " para las fechas anteriores a " + fechaActual);
    }

}

