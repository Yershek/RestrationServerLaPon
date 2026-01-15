package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.MaritalStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MaritalStatusResponse;
import kg.laponandsweezy.registrationlapon.entity.MaritalStatus;
import kg.laponandsweezy.registrationlapon.enums.MaritalStatusEnums;
import kg.laponandsweezy.registrationlapon.mapper.MaritalStatusMapper;
import org.springframework.stereotype.Component;

@Component
public class MaritalStatusMapperImpl implements MaritalStatusMapper {

    @Override
    public MaritalStatus toEntity(MaritalStatusRequest request) {
        if (request == null) {
            return null;
        }
        MaritalStatus maritalStatus = new MaritalStatus();
        maritalStatus.setName(request.getName());
        return maritalStatus;
    }

    @Override
    public MaritalStatusResponse toDto(MaritalStatus entity) {
        if (entity == null) {
            return null;
        }
        MaritalStatusResponse response = new MaritalStatusResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}
