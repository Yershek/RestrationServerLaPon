package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.MarriageRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MarriageResponse;
import kg.laponandsweezy.registrationlapon.entity.Marriage;

public interface MarriageMapper {
    Marriage toEntity(MarriageRequest request);
    MarriageResponse toDto(Marriage entity);
}
