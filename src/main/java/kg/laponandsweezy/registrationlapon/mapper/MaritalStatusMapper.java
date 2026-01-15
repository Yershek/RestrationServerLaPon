package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.MaritalStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MaritalStatusResponse;
import kg.laponandsweezy.registrationlapon.entity.MaritalStatus;

public interface MaritalStatusMapper {
    MaritalStatus toEntity(MaritalStatusRequest request);
    MaritalStatusResponse toDto(MaritalStatus entity);
}
