package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenResidencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResidencyResponse;
import kg.laponandsweezy.registrationlapon.entity.CitizenResidency;

public interface CitizenResidencyMapper {
    CitizenResidency toEntity(CitizenResidencyRequest request);
    CitizenResidencyResponse toDto(CitizenResidency entity);
}
