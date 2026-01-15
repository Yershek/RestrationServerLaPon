package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;

public interface CitizenMapper {
    Citizen toEntity(CitizenRequest request);
    CitizenResponse toDto(Citizen entity);
}
