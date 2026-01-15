package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenContactRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenContactResponse;
import java.util.List;

public interface CitizenContactService {
    CitizenContactResponse save(CitizenContactRequest request);
    CitizenContactResponse findById(int id);
    List<CitizenContactResponse> findAll();
    CitizenContactResponse update(int id, CitizenContactRequest request);
    void deleteById(int id);
}
