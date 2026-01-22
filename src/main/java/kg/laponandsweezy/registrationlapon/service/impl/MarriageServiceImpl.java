package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.MarriageRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MarriageResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.MaritalStatus;
import kg.laponandsweezy.registrationlapon.entity.Marriage;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.MarriageMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.MaritalStatusRepository;
import kg.laponandsweezy.registrationlapon.repository.MarriageRepository;
import kg.laponandsweezy.registrationlapon.service.MarriageService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarriageServiceImpl implements MarriageService {

    private final MarriageRepository marriageRepository;
    private final MarriageMapper marriageMapper;
    private final CitizenRepository citizenRepository;
    private final MaritalStatusRepository maritalStatusRepository;

    public MarriageServiceImpl(MarriageRepository marriageRepository,
                               MarriageMapper marriageMapper,
                               CitizenRepository citizenRepository,
                               MaritalStatusRepository maritalStatusRepository) {
        this.marriageRepository = marriageRepository;
        this.marriageMapper = marriageMapper;
        this.citizenRepository = citizenRepository;
        this.maritalStatusRepository = maritalStatusRepository;
    }

    @Override
    public MarriageResponse save(MarriageRequest request) {
        Marriage marriage = marriageMapper.toEntity(request);
        Citizen husband = citizenRepository.findById(request.getHusbandId())
                .orElseThrow(() -> new ResourceNotFoundException("Husband not found with id: " + request.getHusbandId()));
        marriage.setHusband(husband);

        Citizen wife = citizenRepository.findById(request.getWifeId())
                .orElseThrow(() -> new ResourceNotFoundException("Wife not found with id: " + request.getWifeId()));
        marriage.setWife(wife);

        MaritalStatus maritalStatus = maritalStatusRepository.findById(request.getMaritalStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("MaritalStatus not found with id: " + request.getMaritalStatusId()));
        marriage.setMaritalStatus(maritalStatus);

        Marriage savedMarriage = marriageRepository.save(marriage);
        return marriageMapper.toDto(savedMarriage);
    }

    @Override
    public MarriageResponse findById(int id) {
        Marriage marriage = marriageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marriage not found with id: " + id));
        return marriageMapper.toDto(marriage);
    }

    @Override
    public List<MarriageResponse> findAll() {
        return marriageRepository.findAll().stream()
                .map(marriageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MarriageResponse update(int id, MarriageRequest request) {
        Marriage existingMarriage = marriageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marriage not found with id: " + id));
        Citizen husband = citizenRepository.findById(request.getHusbandId())
                .orElseThrow(() -> new ResourceNotFoundException("Husband not found with id: " + request.getHusbandId()));
        existingMarriage.setHusband(husband);

        Citizen wife = citizenRepository.findById(request.getWifeId())
                .orElseThrow(() -> new ResourceNotFoundException("Wife not found with id: " + request.getWifeId()));
        existingMarriage.setWife(wife);

        MaritalStatus maritalStatus = maritalStatusRepository.findById(request.getMaritalStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("MaritalStatus not found with id: " + request.getMaritalStatusId()));
        existingMarriage.setMaritalStatus(maritalStatus);

        existingMarriage.setMarriageDate(request.getMarriageDate());
        existingMarriage.setRegistrationAuthority(request.getRegistrationAuthority());
        existingMarriage.setDocumentSeriesNumber(request.getDocumentSeriesNumber());
        existingMarriage.setActive(request.isActive());
        existingMarriage.setDivorceDate(request.getDivorceDate());

        Marriage updatedMarriage = marriageRepository.save(existingMarriage);
        return marriageMapper.toDto(updatedMarriage);
    }

    @Override
    public void deleteById(int id) {
        if (!marriageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Marriage not found with id: " + id);
        }
        marriageRepository.deleteById(id);
    }
}