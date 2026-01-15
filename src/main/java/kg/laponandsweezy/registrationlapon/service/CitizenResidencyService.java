package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenResidencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResidencyResponse;
import java.util.List;

public interface CitizenResidencyService {
    CitizenResidencyResponse save(CitizenResidencyRequest request);
    CitizenResidencyResponse findById(int id);
    List<CitizenResidencyResponse> findAll();
    CitizenResidencyResponse update(int id, CitizenResidencyRequest request);
    void deleteById(int id);
}
