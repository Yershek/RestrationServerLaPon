package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentTypeRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentTypeResponse;
import kg.laponandsweezy.registrationlapon.entity.DocumentType;

public interface DocumentTypeMapper {
    DocumentType toEntity(DocumentTypeRequest request);
    DocumentTypeResponse toDto(DocumentType entity);
}
