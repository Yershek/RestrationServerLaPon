package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.AgencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AgencyResponse;
import kg.laponandsweezy.registrationlapon.entity.Agency;

public interface AgencyMapper {
    Agency toEntity(AgencyRequest request);
    AgencyResponse toDto(Agency entity);
}
