package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.DataRequestsLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DataRequestsLogResponse;
import java.util.List;

public interface DataRequestsLogService {
    DataRequestsLogResponse save(DataRequestsLogRequest request);
    DataRequestsLogResponse findById(Long id);
    List<DataRequestsLogResponse> findAll();
    DataRequestsLogResponse update(Long id, DataRequestsLogRequest request);
    void deleteById(Long id);
}
