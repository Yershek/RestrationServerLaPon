package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentTypeRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentTypeResponse;
import kg.laponandsweezy.registrationlapon.entity.DocumentType;
import kg.laponandsweezy.registrationlapon.mapper.DocumentTypeMapper;
import kg.laponandsweezy.registrationlapon.repository.DocumentTypeRepository;
import kg.laponandsweezy.registrationlapon.service.DocumentTypeService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    @Override
    public DocumentTypeResponse save(DocumentTypeRequest request) {
        DocumentType documentType = documentTypeMapper.toEntity(request);
        DocumentType savedDocumentType = documentTypeRepository.save(documentType);
        return documentTypeMapper.toDto(savedDocumentType);
    }

    @Override
    public DocumentTypeResponse findById(int id) {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with id: " + id));
        return documentTypeMapper.toDto(documentType);
    }

    @Override
    public List<DocumentTypeResponse> findAll() {
        return documentTypeRepository.findAll().stream()
                .map(documentTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentTypeResponse update(int id, DocumentTypeRequest request) {
        DocumentType existingDocumentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with id: " + id));

        existingDocumentType.setNameRu(request.getNameRu());
        existingDocumentType.setDescription(request.getDescription());

        DocumentType updatedDocumentType = documentTypeRepository.save(existingDocumentType);
        return documentTypeMapper.toDto(updatedDocumentType);
    }

    @Override
    public void deleteById(int id) {
        documentTypeRepository.deleteById(id);
    }
}
