package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.DataRequestsLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DataRequestsLogResponse;
import kg.laponandsweezy.registrationlapon.entity.DataRequestsLog;

public interface DataRequestsLogMapper {
    DataRequestsLog toEntity(DataRequestsLogRequest request);
    DataRequestsLogResponse toDto(DataRequestsLog entity);
}
