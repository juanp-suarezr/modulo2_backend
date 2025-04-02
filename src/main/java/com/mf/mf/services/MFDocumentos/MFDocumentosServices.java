package com.mf.mf.services.MFDocumentos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.model.MFDocumentos;
import com.mf.mf.repository.MFAnulacion.MFAnexoAnulacionRepository;
import com.mf.mf.repository.MFDocumentos.MFDocumentosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class MFDocumentosServices {

    private final MFDocumentosRepository documentosRepository;

    public MFDocumentos guardarDocumento(MFDocumentos documento) {
        // Verifica si ya existe un documento con el mismo idHeredado
        Optional<MFDocumentos> existente = documentosRepository.findById(Long.valueOf(documento.getIdHeredado()));


        // Si existe, no actualiza nada y devuelve el documento existente
        // Si no existe, lo guarda como un nuevo registro
        return existente.orElseGet(() -> documentosRepository.save(documento));
    }


}
