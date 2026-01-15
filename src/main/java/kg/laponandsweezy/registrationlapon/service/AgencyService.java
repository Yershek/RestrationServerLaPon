package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.AgencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AgencyResponse;
import java.util.List;

public interface AgencyService {
    AgencyResponse save(AgencyRequest request);
    AgencyResponse findById(int id);
    List<AgencyResponse> findAll();
    AgencyResponse update(int id, AgencyRequest request);
    void deleteById(int id);
}
