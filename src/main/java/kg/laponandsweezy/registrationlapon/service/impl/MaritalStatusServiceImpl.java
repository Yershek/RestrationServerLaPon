package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.MaritalStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.MaritalStatusResponse;
import kg.laponandsweezy.registrationlapon.entity.MaritalStatus;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.MaritalStatusMapper;
import kg.laponandsweezy.registrationlapon.repository.MaritalStatusRepository;
import kg.laponandsweezy.registrationlapon.service.MaritalStatusService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaritalStatusServiceImpl implements MaritalStatusService {

    private final MaritalStatusRepository maritalStatusRepository;
    private final MaritalStatusMapper maritalStatusMapper;

    public MaritalStatusServiceImpl(MaritalStatusRepository maritalStatusRepository, MaritalStatusMapper maritalStatusMapper) {
        this.maritalStatusRepository = maritalStatusRepository;
        this.maritalStatusMapper = maritalStatusMapper;
    }

    @Override
    public MaritalStatusResponse save(MaritalStatusRequest request) {
        MaritalStatus maritalStatus = maritalStatusMapper.toEntity(request);
        MaritalStatus savedMaritalStatus = maritalStatusRepository.save(maritalStatus);
        return maritalStatusMapper.toDto(savedMaritalStatus);
    }

    @Override
    public MaritalStatusResponse findById(int id) {
        MaritalStatus maritalStatus = maritalStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MaritalStatus not found with id: " + id));
        return maritalStatusMapper.toDto(maritalStatus);
    }

    @Override
    public List<MaritalStatusResponse> findAll() {
        return maritalStatusRepository.findAll().stream()
                .map(maritalStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaritalStatusResponse update(int id, MaritalStatusRequest request) {
        MaritalStatus existingMaritalStatus = maritalStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MaritalStatus not found with id: " + id));

        existingMaritalStatus.setName(request.getName());

        MaritalStatus updatedMaritalStatus = maritalStatusRepository.save(existingMaritalStatus);
        return maritalStatusMapper.toDto(updatedMaritalStatus);
    }

    @Override
    public void deleteById(int id) {
        if (!maritalStatusRepository.existsById(id)) {
            throw new ResourceNotFoundException("MaritalStatus not found with id: " + id);
        }
        maritalStatusRepository.deleteById(id);
    }
}