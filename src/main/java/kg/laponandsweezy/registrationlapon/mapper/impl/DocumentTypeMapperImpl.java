package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentTypeRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentTypeResponse;
import kg.laponandsweezy.registrationlapon.entity.DocumentType;
import kg.laponandsweezy.registrationlapon.mapper.DocumentTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapperImpl implements DocumentTypeMapper {

    @Override
    public DocumentType toEntity(DocumentTypeRequest request) {
        if (request == null) {
            return null;
        }
        DocumentType documentType = new DocumentType();
        documentType.setNameRu(request.getNameRu());
        documentType.setDescription(request.getDescription());
        return documentType;
    }

    @Override
    public DocumentTypeResponse toDto(DocumentType entity) {
        if (entity == null) {
            return null;
        }
        DocumentTypeResponse response = new DocumentTypeResponse();
        response.setId(entity.getId());
        response.setNameRu(entity.getNameRu());
        response.setDescription(entity.getDescription());
        return response;
    }
}
