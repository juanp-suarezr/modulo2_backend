package com.mf.mf.services.MFDocumentos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mf.mf.model.MFDocumentos;
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

    @Transactional(propagation = Propagation.REQUIRED) // ✅ Se agregó @Transactional con Propagation.REQUIRED
    public MFDocumentos guardarDocumento(MFDocumentos documento) {
        // Verifica si ya existe un documento con el mismo idHeredado
        Optional<MFDocumentos> existente = documentosRepository.findByIdHeredado(documento.getIdHeredado());

        // Si existe, no actualiza nada y devuelve el documento existente
        if (existente.isPresent()) {
            return existente.get();
        }

        // Si no existe, lo guarda como un nuevo registro
        MFDocumentos nuevoDocumento = documentosRepository.save(documento);

        // Actualiza la información relacionada en otra tabla
        mfHeredadosRepository.actualizarCargoExcel(documento.getIdHeredado());

        return nuevoDocumento;
    }
}
