package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.DocumentChangesLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DocumentChangesLogResponse;
import kg.laponandsweezy.registrationlapon.entity.DocumentChangesLog;

public interface DocumentChangesLogMapper {
    DocumentChangesLog toEntity(DocumentChangesLogRequest request);
    DocumentChangesLogResponse toDto(DocumentChangesLog entity);
}
