package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenStatusResponse;
import kg.laponandsweezy.registrationlapon.entity.CitizenStatus;

public interface CitizenStatusMapper {
    CitizenStatus toEntity(CitizenStatusRequest request);
    CitizenStatusResponse toDto(CitizenStatus entity);
}
