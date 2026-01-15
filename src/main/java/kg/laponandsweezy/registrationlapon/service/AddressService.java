package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.AddressRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AddressResponse;
import java.util.List;

public interface AddressService {
    AddressResponse save(AddressRequest request);
    AddressResponse findById(int id);
    List<AddressResponse> findAll();
    AddressResponse update(int id, AddressRequest request);
    void deleteById(int id);
}
