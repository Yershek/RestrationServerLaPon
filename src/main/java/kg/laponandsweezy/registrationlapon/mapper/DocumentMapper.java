package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentResponse;
import kg.laponandsweezy.registrationlapon.entity.Document;

public interface DocumentMapper {
    Document toEntity(DocumentRequest request);
    DocumentResponse toDto(Document entity);
}
