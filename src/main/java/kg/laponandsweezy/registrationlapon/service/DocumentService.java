package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentResponse;
import java.util.List;

public interface DocumentService {
    DocumentResponse save(DocumentRequest request);
    DocumentResponse findById(int id);
    List<DocumentResponse> findAll();
    DocumentResponse update(int id, DocumentRequest request);
    void deleteById(int id);
}
