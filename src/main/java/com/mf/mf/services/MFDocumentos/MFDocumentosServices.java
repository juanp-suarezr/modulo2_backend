package com.mf.mf.services.MFDocumentos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.model.MFDocumentos;
import com.mf.mf.model.MFHashHeredado;
import com.mf.mf.repository.MFAnulacion.MFAnexoAnulacionRepository;
import com.mf.mf.repository.MFDocumentos.MFDocumentosRepository;
import com.mf.mf.repository.MFHeredadosRepository.MFHeredadosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class MFDocumentosServices {

    private final MFDocumentosRepository documentosRepository;
    private final MFHeredadosRepository mfHeredadosRepository; // ✅ Se añadió este repositorio

    @Transactional(propagation = Propagation.REQUIRED)
    public MFDocumentos guardarDocumento(MFDocumentos documento) {
        return guardarDocumento(documento, false); // valor por defecto
    }

    //GUARDAR 1 SOLO ARCHIVO
    @Transactional(propagation = Propagation.REQUIRED)
    public MFDocumentos guardarDocumento(MFDocumentos documento, boolean isFinalStep) {
        Optional<MFDocumentos> existente = documentosRepository.findByIdHeredado(documento.getIdHeredado());
        Optional<MFHashHeredado> heredadoOpt = mfHeredadosRepository.findByIdHeredado(documento.getIdHeredado());

        MFHashHeredado heredado = heredadoOpt.get();

        if (existente.isPresent()) {
            return existente.get();
        }

        documento.setEstado(true);
        MFDocumentos nuevoDocumento = documentosRepository.save(documento);

        mfHeredadosRepository.actualizarCargoExcel(documento.getIdHeredado());

        if (isFinalStep) {
            int estado = (heredado.getEstadoEntrega() == 286) ? 460 : 289;
            mfHeredadosRepository.actualizarEstadoEntrega(documento.getIdHeredado(), estado);
        }

        return nuevoDocumento;
    }

    //GUARDAR MULTI DOCS
    @Transactional(propagation = Propagation.REQUIRED)
    public MFDocumentos guardarMultiDocumento(MFDocumentos documento, boolean isFinalStep) {
        Optional<MFDocumentos> existente = documentosRepository.findByIdHeredado(documento.getIdHeredado());
        Optional<MFHashHeredado> heredadoOpt = mfHeredadosRepository.findByIdHeredado(documento.getIdHeredado());

        MFHashHeredado heredado = heredadoOpt.get();


        documento.setEstado(true);
        MFDocumentos nuevoDocumento = documentosRepository.save(documento);

        mfHeredadosRepository.actualizarCargoExcel(documento.getIdHeredado());

        if (isFinalStep && !existente.isPresent()) {
            int estado = (heredado.getEstadoEntrega() == 286) ? 460 : 289;
            mfHeredadosRepository.actualizarEstadoEntrega(documento.getIdHeredado(), estado);
        }

        return nuevoDocumento;
    }


}
