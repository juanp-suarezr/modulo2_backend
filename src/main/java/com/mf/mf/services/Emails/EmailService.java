package com.mf.mf.services.Emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${domainurl}")
    private String domain;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("supertransportedigital@supertransporte.gov.co");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendEmailAprobado(
            String to,
            String to1,
            String tipoReporte,
            String fechaSolicitud,
            String nombre,
            String razonSocial,
            String nit) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("supertransportedigital@supertransporte.gov.co");
        message.setTo(to);
        // Agregar `to1` solo si no es nulo ni vacío
        if (to1 != null && !to1.trim().isEmpty()) {
            message.setCc(to1); // Agregar el segundo correo en copia
        }
        message.setSubject("Correo aprobación de solicitud de anulación.");
        String emailText = String.format(
                "Estimado(a) %s,\n\n" +
                        "Nos permitimos informarle que su solicitud de anulación del reporte **%s** ha sido aprobada.\n" +
                        "Detalles de la solicitud:\n" +
                        "- **Fecha de solicitud:** %s\n" +
                        "- **Nombre:** %s\n" +
                        "- **Razón Social:** %s\n" +
                        "- **NIT:** %s\n\n" +
                        "A partir de ahora, puede ingresar al modulo financiero y actualizar su reporte en entregas pendientes. Una vez actualizado, este se guardará en estado de retransmisión y lo podrá consultar en el apartado de consultar entregas:\n" +
                        "Gracias por su cooperación y cumplimiento con los requisitos establecidos.\n\n" +
                        "Atentamente,\n" +
                        "**Superintendencia de Transporte**",
                nombre, tipoReporte, fechaSolicitud, nombre, razonSocial, nit);

        message.setText(emailText);
        javaMailSender.send(message);
    }


    public void sendEmailRechazado(
            String to,
            String to1,
            String tipoReporte,
            String fechaSolicitud,
            String nombre,
            String razonSocial,
            String nit,
            String observacion) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("supertransportedigital@supertransporte.gov.co");
        message.setTo(to);

        // Agregar `to1` solo si no es nulo ni vacío
        if (to1 != null && !to1.trim().isEmpty()) {
            message.setCc(to1);
        }

        if (Objects.equals(observacion, "NA")) {
            observacion = "no es viable la solicitud";
        }

        message.setSubject("Correo rechazo de solicitud de anulación.");

        String emailText = String.format(
                "Estimado(a) %s,\n\n" +
                        "Lamentamos informarle que su solicitud de anulación del reporte **%s** ha sido rechazada.\n" +
                        "Detalles de la solicitud:\n" +
                        "- **Fecha de solicitud:** %s\n" +
                        "- **Nombre:** %s\n" +
                        "- **Razón Social:** %s\n" +
                        "- **NIT:** %s\n" +
                        "- **Motivo de rechazo:** %s\n\n" +
                        "Si considera que esto es un error, le sugerimos comunicarse con la Superintendencia de Transporte para mayor información.\n\n" +
                        "Atentamente,\n" +
                        "**Superintendencia de Transporte**",
                nombre, tipoReporte, fechaSolicitud, nombre, razonSocial, nit, observacion);

        message.setText(emailText);
        javaMailSender.send(message);
    }

}
