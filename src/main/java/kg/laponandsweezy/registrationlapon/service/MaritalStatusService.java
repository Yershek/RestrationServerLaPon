package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.MaritalStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MaritalStatusResponse;
import java.util.List;

public interface MaritalStatusService {
    MaritalStatusResponse save(MaritalStatusRequest request);
    MaritalStatusResponse findById(int id);
    List<MaritalStatusResponse> findAll();
    MaritalStatusResponse update(int id, MaritalStatusRequest request);
    void deleteById(int id);
}
