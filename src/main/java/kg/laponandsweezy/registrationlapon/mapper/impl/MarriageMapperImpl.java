package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.MarriageRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MarriageResponse;
import kg.laponandsweezy.registrationlapon.entity.Marriage;
import kg.laponandsweezy.registrationlapon.mapper.MarriageMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.MaritalStatusRepository;
import kg.laponandsweezy.registrationlapon.repository.MarriageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarriageMapperImpl implements MarriageMapper {

    CitizenRepository citizenRepository;
    MaritalStatusRepository maritalStatusRepository;

    @Autowired
    public MarriageMapperImpl(CitizenRepository citizenRepository, MaritalStatusRepository maritalStatusRepository) {
        this.citizenRepository = citizenRepository;
        this.maritalStatusRepository = maritalStatusRepository;
    }

    @Override
    public Marriage toEntity(MarriageRequest request) {
        if (request == null) {
            return null;
        }
        Marriage marriage = new Marriage();
        marriage.setHusband(citizenRepository.findById(request.getHusbandId()).orElse(null));
        marriage.setWife(citizenRepository.findById(request.getWifeId()).orElse(null));
        marriage.setMaritalStatus(maritalStatusRepository.findById(request.getMaritalStatusId()).orElse(null));
        marriage.setMarriageDate(request.getMarriageDate());
        marriage.setRegistrationAuthority(request.getRegistrationAuthority());
        marriage.setDocumentSeriesNumber(request.getDocumentSeriesNumber());
        marriage.setActive(request.isActive());
        marriage.setDivorceDate(request.getDivorceDate());
        return marriage;
    }

    @Override
    public MarriageResponse toDto(Marriage entity) {
        if (entity == null) {
            return null;
        }
        MarriageResponse response = new MarriageResponse();
        response.setId(entity.getId());
        response.setHusbandId(entity.getHusband() != null ? entity.getHusband().getId() : 0);
        response.setWifeId(entity.getWife() != null ? entity.getWife().getId() : 0);
        response.setMaritalStatusId(entity.getMaritalStatus() != null ? entity.getMaritalStatus().getId() : 0);
        response.setMarriageDate(entity.getMarriageDate());
        response.setRegistrationAuthority(entity.getRegistrationAuthority());
        response.setDocumentSeriesNumber(entity.getDocumentSeriesNumber());
        response.setActive(entity.isActive());
        response.setDivorceDate(entity.getDivorceDate());
        return response;
    }
}
