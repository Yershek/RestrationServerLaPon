package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.AddressRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AddressResponse;
import kg.laponandsweezy.registrationlapon.entity.Address;

public interface AddressMapper {
    Address toEntity(AddressRequest request);
    AddressResponse toDto(Address entity);
}
