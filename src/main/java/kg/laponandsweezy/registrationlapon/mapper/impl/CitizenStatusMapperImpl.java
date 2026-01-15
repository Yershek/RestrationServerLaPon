package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenStatusResponse;
import kg.laponandsweezy.registrationlapon.entity.CitizenStatus;
import kg.laponandsweezy.registrationlapon.mapper.CitizenStatusMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CitizenStatusMapperImpl implements CitizenStatusMapper {

    CitizenRepository citizenRepository;

    @Autowired
    public CitizenStatusMapperImpl(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @Override
    public CitizenStatus toEntity(CitizenStatusRequest request) {
        if (request == null) {
            return null;
        }
        CitizenStatus citizenStatus = new CitizenStatus();
        citizenStatus.setCitizen(citizenRepository.findById(request.getCitizenId()).orElse(null));
        citizenStatus.setStatusType(request.getStatusType());
        citizenStatus.setStatusDate(request.getStatusDate());
        citizenStatus.setReferenceDocument(request.getReferenceDocument());
        return citizenStatus;
    }

    @Override
    public CitizenStatusResponse toDto(CitizenStatus entity) {
        if (entity == null) {
            return null;
        }
        CitizenStatusResponse response = new CitizenStatusResponse();
        response.setId(entity.getId());
        response.setCitizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : 0);
        response.setStatusType(entity.getStatusType());
        response.setStatusDate(entity.getStatusDate());
        response.setReferenceDocument(entity.getReferenceDocument());
        return response;
    }
}
