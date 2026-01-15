package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenStatusRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenStatusResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.CitizenStatus;
import kg.laponandsweezy.registrationlapon.mapper.CitizenStatusMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.CitizenStatusRepository;
import kg.laponandsweezy.registrationlapon.service.CitizenStatusService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenStatusServiceImpl implements CitizenStatusService {

    private final CitizenStatusRepository citizenStatusRepository;
    private final CitizenStatusMapper citizenStatusMapper;
    private final CitizenRepository citizenRepository;

    public CitizenStatusServiceImpl(CitizenStatusRepository citizenStatusRepository,
                                    CitizenStatusMapper citizenStatusMapper,
                                    CitizenRepository citizenRepository) {
        this.citizenStatusRepository = citizenStatusRepository;
        this.citizenStatusMapper = citizenStatusMapper;
        this.citizenRepository = citizenRepository;
    }

    @Override
    public CitizenStatusResponse save(CitizenStatusRequest request) {
        CitizenStatus citizenStatus = citizenStatusMapper.toEntity(request);
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + request.getCitizenId()));
        citizenStatus.setCitizen(citizen);

        CitizenStatus savedStatus = citizenStatusRepository.save(citizenStatus);
        return citizenStatusMapper.toDto(savedStatus);
    }

    @Override
    public CitizenStatusResponse findById(int id) {
        CitizenStatus citizenStatus = citizenStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CitizenStatus not found with id: " + id));
        return citizenStatusMapper.toDto(citizenStatus);
    }

    @Override
    public List<CitizenStatusResponse> findAll() {
        return citizenStatusRepository.findAll().stream()
                .map(citizenStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CitizenStatusResponse update(int id, CitizenStatusRequest request) {
        CitizenStatus existingStatus = citizenStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CitizenStatus not found with id: " + id));
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + request.getCitizenId()));
        existingStatus.setCitizen(citizen);

        existingStatus.setStatusType(request.getStatusType());
        existingStatus.setStatusDate(request.getStatusDate());
        existingStatus.setReferenceDocument(request.getReferenceDocument());

        CitizenStatus updatedStatus = citizenStatusRepository.save(existingStatus);
        return citizenStatusMapper.toDto(updatedStatus);
    }

    @Override
    public void deleteById(int id) {
        citizenStatusRepository.deleteById(id);
    }
}
