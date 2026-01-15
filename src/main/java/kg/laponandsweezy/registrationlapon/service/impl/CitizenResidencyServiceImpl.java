package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenResidencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResidencyResponse;
import kg.laponandsweezy.registrationlapon.entity.Address;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.CitizenResidency;
import kg.laponandsweezy.registrationlapon.mapper.CitizenResidencyMapper;
import kg.laponandsweezy.registrationlapon.repository.AddressRepository;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.CitizenResidencyRepository;
import kg.laponandsweezy.registrationlapon.service.CitizenResidencyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenResidencyServiceImpl implements CitizenResidencyService {

    private final CitizenResidencyRepository citizenResidencyRepository;
    private final CitizenResidencyMapper citizenResidencyMapper;
    private final CitizenRepository citizenRepository;
    private final AddressRepository addressRepository;

    public CitizenResidencyServiceImpl(CitizenResidencyRepository citizenResidencyRepository,
                                       CitizenResidencyMapper citizenResidencyMapper,
                                       CitizenRepository citizenRepository,
                                       AddressRepository addressRepository) {
        this.citizenResidencyRepository = citizenResidencyRepository;
        this.citizenResidencyMapper = citizenResidencyMapper;
        this.citizenRepository = citizenRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public CitizenResidencyResponse save(CitizenResidencyRequest request) {
        CitizenResidency citizenResidency = citizenResidencyMapper.toEntity(request);
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + request.getCitizenId()));
        citizenResidency.setCitizen(citizen);

        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + request.getAddressId()));
        citizenResidency.setAddress(address);

        CitizenResidency savedResidency = citizenResidencyRepository.save(citizenResidency);
        return citizenResidencyMapper.toDto(savedResidency);
    }

    @Override
    public CitizenResidencyResponse findById(int id) {
        CitizenResidency citizenResidency = citizenResidencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CitizenResidency not found with id: " + id));
        return citizenResidencyMapper.toDto(citizenResidency);
    }

    @Override
    public List<CitizenResidencyResponse> findAll() {
        return citizenResidencyRepository.findAll().stream()
                .map(citizenResidencyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CitizenResidencyResponse update(int id, CitizenResidencyRequest request) {
        CitizenResidency existingResidency = citizenResidencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CitizenResidency not found with id: " + id));
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + request.getCitizenId()));
        existingResidency.setCitizen(citizen);

        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + request.getAddressId()));
        existingResidency.setAddress(address);

        existingResidency.setAddressType(request.getAddressType());
        existingResidency.setStartDate(request.getStartDate());
        existingResidency.setEndDate(request.getEndDate());

        CitizenResidency updatedResidency = citizenResidencyRepository.save(existingResidency);
        return citizenResidencyMapper.toDto(updatedResidency);
    }

    @Override
    public void deleteById(int id) {
        citizenResidencyRepository.deleteById(id);
    }
}
