package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentResponse;
import kg.laponandsweezy.registrationlapon.entity.Document;
import kg.laponandsweezy.registrationlapon.mapper.DocumentMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapperImpl implements DocumentMapper {

    CitizenRepository citizenRepository;
    DocumentTypeRepository documentTypeRepository;

    @Autowired
    public DocumentMapperImpl(CitizenRepository citizenRepository, DocumentTypeRepository documentTypeRepository) {
        this.citizenRepository = citizenRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public Document toEntity(DocumentRequest request) {
        if (request == null) {
            return null;
        }
        Document document = new Document();
        document.setCitizen(citizenRepository.findById(request.getCitizenId()).orElse(null));
        document.setDocumentType(documentTypeRepository.findById(request.getDocumentTypeId()).orElse(null));
        document.setSeries(request.getSeries());
        document.setNumber(request.getNumber());
        document.setIssueDate(request.getIssueDate());
        document.setExpiryDate(request.getExpiryDate());
        document.setIssuingAuthority(request.getIssuingAuthority());
        document.setActive(request.isActive());
        return document;
    }

    @Override
    public DocumentResponse toDto(Document entity) {
        if (entity == null) {
            return null;
        }
        DocumentResponse response = new DocumentResponse();
        response.setId(entity.getId());
        response.setCitizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : 0);
        response.setDocumentTypeId(entity.getDocumentType() != null ? entity.getDocumentType().getId() : 0);
        response.setSeries(entity.getSeries());
        response.setNumber(entity.getNumber());
        response.setIssueDate(entity.getIssueDate());
        response.setExpiryDate(entity.getExpiryDate());
        response.setIssuingAuthority(entity.getIssuingAuthority());
        response.setActive(entity.isActive());
        return response;
    }
}
