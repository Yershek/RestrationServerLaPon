package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.MarriageRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MarriageResponse;
import java.util.List;

public interface MarriageService {
    MarriageResponse save(MarriageRequest request);
    MarriageResponse findById(int id);
    List<MarriageResponse> findAll();
    MarriageResponse update(int id, MarriageRequest request);
    void deleteById(int id);
}
