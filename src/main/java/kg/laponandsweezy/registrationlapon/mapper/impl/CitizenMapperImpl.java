package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.mapper.CitizenMapper;
import org.springframework.stereotype.Component;

@Component
public class CitizenMapperImpl implements CitizenMapper {

    @Override
    public Citizen toEntity(CitizenRequest request) {
        if (request == null) {
            return null;
        }
        Citizen citizen = new Citizen();
        citizen.setPassword(request.getPassword());
        citizen.setPersonalIdNumber(request.getPersonalIdNumber());
        citizen.setLastName(request.getLastName());
        citizen.setFirstName(request.getFirstName());
        citizen.setMiddleName(request.getMiddleName());
        citizen.setDateOfBirth(request.getDateOfBirth());
        citizen.setGender(request.getGender());
        citizen.setPlaceOfBirth(request.getPlaceOfBirth());
        citizen.setNationality(request.getNationality());
        return citizen;
    }

    @Override
    public CitizenResponse toDto(Citizen entity) {
        if (entity == null) {
            return null;
        }
        CitizenResponse response = new CitizenResponse();
        response.setId(entity.getId());
        response.setPersonalIdNumber(entity.getPersonalIdNumber());
        response.setLastName(entity.getLastName());
        response.setFirstName(entity.getFirstName());
        response.setMiddleName(entity.getMiddleName());
        response.setDateOfBirth(entity.getDateOfBirth());
        response.setGender(entity.getGender());
        response.setPlaceOfBirth(entity.getPlaceOfBirth());
        response.setNationality(entity.getNationality());
        return response;
    }
}
