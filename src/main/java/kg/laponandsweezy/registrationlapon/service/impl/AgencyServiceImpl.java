package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.AgencyRequest;
import kg.laponandsweezy.registrationlapon.dto.response.AgencyResponse;
import kg.laponandsweezy.registrationlapon.entity.Agency;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.AgencyMapper;
import kg.laponandsweezy.registrationlapon.repository.AgencyRepository;
import kg.laponandsweezy.registrationlapon.service.AgencyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;

    public AgencyServiceImpl(AgencyRepository agencyRepository, AgencyMapper agencyMapper) {
        this.agencyRepository = agencyRepository;
        this.agencyMapper = agencyMapper;
    }

    @Override
    public AgencyResponse save(AgencyRequest request) {
        Agency agency = agencyMapper.toEntity(request);
        Agency savedAgency = agencyRepository.save(agency);
        return agencyMapper.toDto(savedAgency);
    }

    @Override
    public AgencyResponse findById(int id) {
        Agency agency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));
        return agencyMapper.toDto(agency);
    }

    @Override
    public List<AgencyResponse> findAll() {
        return agencyRepository.findAll().stream()
                .map(agencyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgencyResponse update(int id, AgencyRequest request) {
        Agency existingAgency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));

        existingAgency.setName(request.getName());
        existingAgency.setShortCode(request.getShortCode());

        Agency updatedAgency = agencyRepository.save(existingAgency);
        return agencyMapper.toDto(updatedAgency);
    }

    @Override
    public void deleteById(int id) {
        if (!agencyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agency not found with id: " + id);
        }
        agencyRepository.deleteById(id);
    }
}