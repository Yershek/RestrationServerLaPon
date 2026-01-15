package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentTypeRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentTypeResponse;
import java.util.List;

public interface DocumentTypeService {
    DocumentTypeResponse save(DocumentTypeRequest request);
    DocumentTypeResponse findById(int id);
    List<DocumentTypeResponse> findAll();
    DocumentTypeResponse update(int id, DocumentTypeRequest request);
    void deleteById(int id);
}
