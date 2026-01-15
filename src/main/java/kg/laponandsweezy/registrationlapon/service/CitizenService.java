package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CitizenService extends UserDetailsService {
    CitizenResponse save(CitizenRequest request);
    CitizenResponse findById(int id);
    List<CitizenResponse> findAll();
    CitizenResponse update(int id, CitizenRequest request);
    void deleteById(int id);
}
