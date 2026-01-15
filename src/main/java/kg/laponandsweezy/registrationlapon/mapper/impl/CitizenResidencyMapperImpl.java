package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenResidencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResidencyResponse;
import kg.laponandsweezy.registrationlapon.entity.CitizenResidency;
import kg.laponandsweezy.registrationlapon.mapper.CitizenResidencyMapper;
import kg.laponandsweezy.registrationlapon.repository.AddressRepository;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CitizenResidencyMapperImpl implements CitizenResidencyMapper {

    CitizenRepository citizenRepository;
    AddressRepository addressRepository;

    @Autowired
    public CitizenResidencyMapperImpl(CitizenRepository citizenRepository, AddressRepository addressRepository) {
        this.citizenRepository = citizenRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public CitizenResidency toEntity(CitizenResidencyRequest request) {
        if (request == null) {
            return null;
        }
        CitizenResidency citizenResidency = new CitizenResidency();
        citizenResidency.setCitizen(citizenRepository.findById(request.getCitizenId()).orElse(null));
        citizenResidency.setAddress(addressRepository.findById(request.getAddressId()).orElse(null));
        citizenResidency.setAddressType(request.getAddressType());
        citizenResidency.setStartDate(request.getStartDate());
        citizenResidency.setEndDate(request.getEndDate());
        return citizenResidency;
    }

    @Override
    public CitizenResidencyResponse toDto(CitizenResidency entity) {
        if (entity == null) {
            return null;
        }
        CitizenResidencyResponse response = new CitizenResidencyResponse();
        response.setId(entity.getId());
        response.setCitizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : 0);
        response.setAddressId(entity.getAddress() != null ? entity.getAddress().getId() : 0);
        response.setAddressType(entity.getAddressType());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        return response;
    }
}
