package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenContactRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenContactResponse;
import kg.laponandsweezy.registrationlapon.entity.CitizenContact;
import kg.laponandsweezy.registrationlapon.mapper.CitizenContactMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CitizenContactMapperImpl implements CitizenContactMapper {

    CitizenRepository citizenRepository;

    @Autowired
    public CitizenContactMapperImpl(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @Override
    public CitizenContact toEntity(CitizenContactRequest request) {
        if (request == null) {
            return null;
        }
        CitizenContact citizenContact = new CitizenContact();
        citizenContact.setCitizen(citizenRepository.findById(request.getCitizenId()).orElse(null));
        citizenContact.setContactType(request.getContactType());
        citizenContact.setContactValue(request.getContactValue());
        citizenContact.setPrimary(request.isPrimary());
        return citizenContact;
    }

    @Override
    public CitizenContactResponse toDto(CitizenContact entity) {
        if (entity == null) {
            return null;
        }
        CitizenContactResponse response = new CitizenContactResponse();
        response.setId(entity.getId());
        response.setCitizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : 0);
        response.setContactType(entity.getContactType());
        response.setContactValue(entity.getContactValue());
        response.setPrimary(entity.isPrimary());
        return response;
    }
}
