package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentChangesLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentChangesLogResponse;
import kg.laponandsweezy.registrationlapon.entity.DocumentChangesLog;
import kg.laponandsweezy.registrationlapon.mapper.DocumentChangesLogMapper;
import kg.laponandsweezy.registrationlapon.repository.AgencyRepository;
import kg.laponandsweezy.registrationlapon.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentChangesLogMapperImpl implements DocumentChangesLogMapper {

    AgencyRepository agencyRepository;
    DocumentRepository documentRepository;

    @Autowired
    public DocumentChangesLogMapperImpl(AgencyRepository agencyRepository, DocumentRepository documentRepository) {
        this.agencyRepository = agencyRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public DocumentChangesLog toEntity(DocumentChangesLogRequest request) {
        if (request == null) {
            return null;
        }
        DocumentChangesLog documentChangesLog = new DocumentChangesLog();
        documentChangesLog.setDocument(documentRepository.findById(request.getDocumentId()).orElse(null));
        documentChangesLog.setChangeType(request.getChangeType());
        documentChangesLog.setAgency(agencyRepository.findById(request.getAgencyId()).orElse(null));
        documentChangesLog.setReason(request.getReason());
        return documentChangesLog;
    }

    @Override
    public DocumentChangesLogResponse toDto(DocumentChangesLog entity) {
        if (entity == null) {
            return null;
        }
        DocumentChangesLogResponse response = new DocumentChangesLogResponse();
        response.setId(entity.getId());
        response.setDocumentId(entity.getDocument() != null ? entity.getDocument().getId() : 0);
        response.setChangeDate(entity.getChangeDate());
        response.setChangeType(entity.getChangeType());
        response.setAgencyId(entity.getAgency() != null ? entity.getAgency().getId() : 0);
        response.setReason(entity.getReason());
        return response;
    }
}
