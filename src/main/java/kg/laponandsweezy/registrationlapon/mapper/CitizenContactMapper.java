package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenContactRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenContactResponse;
import kg.laponandsweezy.registrationlapon.entity.CitizenContact;

public interface CitizenContactMapper {
    CitizenContact toEntity(CitizenContactRequest request);
    CitizenContactResponse toDto(CitizenContact entity);
}
