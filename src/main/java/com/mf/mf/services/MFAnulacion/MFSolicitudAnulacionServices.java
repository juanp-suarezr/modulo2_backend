package com.mf.mf.services.MFAnulacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.model.anulacion.MFAnexosAnulacion;
import com.mf.mf.projection.MFAnulacion.GetMFAnexoAnulacionProjection;
import com.mf.mf.projection.MFAnulacion.GetMFSolicitudAnulacionProjection;
import com.mf.mf.repository.MFAnulacion.MFAnexoAnulacionRepository;
import com.mf.mf.repository.MFAnulacion.MFSolicitudAnulacionRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import com.mf.mf.services.Emails.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class MFSolicitudAnulacionServices {

    private final MFSolicitudAnulacionRepository mfSolicitudAnulacionRepository;
    private final MFAnexoAnulacionRepository mfAnexosAnulacionRepository;
    //heredado repository
    private final MFHeredadosRepository mfHeredadosRepository;
    //EMAIL SERVICE
    private final EmailService emailService;

    /**
     * Obtiene una solicitud de anulación por su ID con sus anexos.
     */
    public Optional<GetMFSolicitudAnulacionProjection> obtenerSolicitudConAnexos(Integer id) {
        return mfSolicitudAnulacionRepository.findById(id).stream().findFirst();
    }

    /**
     * Obtiene los anexos de una solicitud de anulación por su ID.
     */
    public List<GetMFAnexoAnulacionProjection> obtenerAnexosPorSolicitud(Integer idAnulacion) {
        return mfAnexosAnulacionRepository.findAnexosByAnulado(idAnulacion);
    }

    /**
     * Actualiza el estado y la observación de una solicitud de anulación.
     *
     * @param estadoReq  Nuevo estado de la solicitud.
     * @param observacion Observación asociada a la actualización.
     * @param id ID de la solicitud de anulación.
     */
    @Transactional
    public void actualizarEstadoSolicitud(String estadoReq, String observacion, Integer id) {
        // Actualizar estado en la base de datos
        mfSolicitudAnulacionRepository.actualizarSolicitudAnulacion(estadoReq, observacion, Long.valueOf(id));

        // Si el estado es "Aprobado", enviar correo

            List<GetMFSolicitudAnulacionProjection> solicitudes = mfSolicitudAnulacionRepository.findById(id);

            // Verificar si hay al menos una solicitud en la lista
            if (!solicitudes.isEmpty()) {
                GetMFSolicitudAnulacionProjection datos = solicitudes.get(0); // Tomamos el primer elemento de la lista

                // Recuperar datos
                String to = datos.getEmail();
                String to1 = datos.getEmail1();
                String tipoReporte = datos.getTipoRequerimientoDescripcion();
                String fechaSolicitud = datos.getFechaSolicitud().toString();
                String nombre = datos.getNombre();
                String razonSocial = datos.getRazonSocial();
                String nit = datos.getNit().toString();

                // Enviar correo
                if ("Aprobado".equalsIgnoreCase(estadoReq)) {
                    mfHeredadosRepository.actualizarEstadoEntrega(datos.getIdHeredado(), 460);
                    emailService.sendEmailAprobado(to, to1, tipoReporte, fechaSolicitud, nombre, razonSocial, nit);
                } else {
                    emailService.sendEmailRechazado(to, to1, tipoReporte, fechaSolicitud, nombre, razonSocial, nit, observacion);
                }

            }

    }


}
