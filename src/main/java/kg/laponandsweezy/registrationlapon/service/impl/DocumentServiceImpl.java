package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.Document;
import kg.laponandsweezy.registrationlapon.entity.DocumentType;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.DocumentMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.DocumentRepository;
import kg.laponandsweezy.registrationlapon.repository.DocumentTypeRepository;
import kg.laponandsweezy.registrationlapon.service.DocumentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final CitizenRepository citizenRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository,
                               DocumentMapper documentMapper,
                               CitizenRepository citizenRepository,
                               DocumentTypeRepository documentTypeRepository) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.citizenRepository = citizenRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public DocumentResponse save(DocumentRequest request) {
        Document document = documentMapper.toEntity(request);
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + request.getCitizenId()));
        document.setCitizen(citizen);

        DocumentType documentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("DocumentType not found with id: " + request.getDocumentTypeId()));
        document.setDocumentType(documentType);

        Document savedDocument = documentRepository.save(document);
        return documentMapper.toDto(savedDocument);
    }

    @Override
    public DocumentResponse findById(int id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
        return documentMapper.toDto(document);
    }

    @Override
    public List<DocumentResponse> findAll() {
        return documentRepository.findAll().stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentResponse update(int id, DocumentRequest request) {
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + request.getCitizenId()));
        existingDocument.setCitizen(citizen);

        DocumentType documentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("DocumentType not found with id: " + request.getDocumentTypeId()));
        existingDocument.setDocumentType(documentType);

        existingDocument.setSeries(request.getSeries());
        existingDocument.setNumber(request.getNumber());
        existingDocument.setIssueDate(request.getIssueDate());
        existingDocument.setExpiryDate(request.getExpiryDate());
        existingDocument.setIssuingAuthority(request.getIssuingAuthority());
        existingDocument.setActive(request.isActive());

        Document updatedDocument = documentRepository.save(existingDocument);
        return documentMapper.toDto(updatedDocument);
    }

    @Override
    public void deleteById(int id) {
        if (!documentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Document not found with id: " + id);
        }
        documentRepository.deleteById(id);
    }
}