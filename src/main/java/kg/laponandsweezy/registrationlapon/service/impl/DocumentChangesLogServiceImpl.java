package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentChangesLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentChangesLogResponse;
import kg.laponandsweezy.registrationlapon.entity.Agency;
import kg.laponandsweezy.registrationlapon.entity.Document;
import kg.laponandsweezy.registrationlapon.entity.DocumentChangesLog;
import kg.laponandsweezy.registrationlapon.mapper.DocumentChangesLogMapper;
import kg.laponandsweezy.registrationlapon.repository.AgencyRepository;
import kg.laponandsweezy.registrationlapon.repository.DocumentChangesLogRepository;
import kg.laponandsweezy.registrationlapon.repository.DocumentRepository;
import kg.laponandsweezy.registrationlapon.service.DocumentChangesLogService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentChangesLogServiceImpl implements DocumentChangesLogService {

    private final DocumentChangesLogRepository documentChangesLogRepository;
    private final DocumentChangesLogMapper documentChangesLogMapper;
    private final DocumentRepository documentRepository;
    private final AgencyRepository agencyRepository;

    public DocumentChangesLogServiceImpl(DocumentChangesLogRepository documentChangesLogRepository,
                                         DocumentChangesLogMapper documentChangesLogMapper,
                                         DocumentRepository documentRepository,
                                         AgencyRepository agencyRepository) {
        this.documentChangesLogRepository = documentChangesLogRepository;
        this.documentChangesLogMapper = documentChangesLogMapper;
        this.documentRepository = documentRepository;
        this.agencyRepository = agencyRepository;
    }

    @Override
    public DocumentChangesLogResponse save(DocumentChangesLogRequest request) {
        DocumentChangesLog documentChangesLog = documentChangesLogMapper.toEntity(request);
        Document document = documentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + request.getDocumentId()));
        documentChangesLog.setDocument(document);

        if (request.getAgencyId() != 0) { // Assuming 0 means no agency associated
            Agency agency = agencyRepository.findById(request.getAgencyId())
                    .orElseThrow(() -> new RuntimeException("Agency not found with id: " + request.getAgencyId()));
            documentChangesLog.setAgency(agency);
        }

        DocumentChangesLog savedLog = documentChangesLogRepository.save(documentChangesLog);
        return documentChangesLogMapper.toDto(savedLog);
    }

    @Override
    public DocumentChangesLogResponse findById(Long id) {
        DocumentChangesLog documentChangesLog = documentChangesLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentChangesLog not found with id: " + id));
        return documentChangesLogMapper.toDto(documentChangesLog);
    }

    @Override
    public List<DocumentChangesLogResponse> findAll() {
        return documentChangesLogRepository.findAll().stream()
                .map(documentChangesLogMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentChangesLogResponse update(Long id, DocumentChangesLogRequest request) {
        DocumentChangesLog existingLog = documentChangesLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentChangesLog not found with id: " + id));
        Document document = documentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + request.getDocumentId()));
        existingLog.setDocument(document);

        if (request.getAgencyId() != 0) {
            Agency agency = agencyRepository.findById(request.getAgencyId())
                    .orElseThrow(() -> new RuntimeException("Agency not found with id: " + request.getAgencyId()));
            existingLog.setAgency(agency);
        } else {
            existingLog.setAgency(null);
        }

        existingLog.setChangeType(request.getChangeType());
        existingLog.setReason(request.getReason());

        DocumentChangesLog updatedLog = documentChangesLogRepository.save(existingLog);
        return documentChangesLogMapper.toDto(updatedLog);
    }

    @Override
    public void deleteById(Long id) {
        documentChangesLogRepository.deleteById(id);
    }
}
