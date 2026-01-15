package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenStatusResponse;
import java.util.List;

public interface CitizenStatusService {
    CitizenStatusResponse save(CitizenStatusRequest request);
    CitizenStatusResponse findById(int id);
    List<CitizenStatusResponse> findAll();
    CitizenStatusResponse update(int id, CitizenStatusRequest request);
    void deleteById(int id);
}
