package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentChangesLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentChangesLogResponse;
import java.util.List;

public interface DocumentChangesLogService {
    DocumentChangesLogResponse save(DocumentChangesLogRequest request);
    DocumentChangesLogResponse findById(Long id);
    List<DocumentChangesLogResponse> findAll();
    DocumentChangesLogResponse update(Long id, DocumentChangesLogRequest request);
    void deleteById(Long id);
}
