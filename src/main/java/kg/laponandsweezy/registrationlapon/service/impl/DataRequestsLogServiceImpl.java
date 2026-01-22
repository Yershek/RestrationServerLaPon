package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.DataRequestsLogRequest;
import kg.laponandsweezy.registrationlapon.dto.response.DataRequestsLogResponse;
import kg.laponandsweezy.registrationlapon.entity.Agency;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.DataRequestsLog;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.DataRequestsLogMapper;
import kg.laponandsweezy.registrationlapon.repository.AgencyRepository;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.DataRequestsLogRepository;
import kg.laponandsweezy.registrationlapon.service.DataRequestsLogService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataRequestsLogServiceImpl implements DataRequestsLogService {

    private final DataRequestsLogRepository dataRequestsLogRepository;
    private final DataRequestsLogMapper dataRequestsLogMapper;
    private final AgencyRepository agencyRepository;
    private final CitizenRepository citizenRepository;

    public DataRequestsLogServiceImpl(DataRequestsLogRepository dataRequestsLogRepository,
                                      DataRequestsLogMapper dataRequestsLogMapper,
                                      AgencyRepository agencyRepository,
                                      CitizenRepository citizenRepository) {
        this.dataRequestsLogRepository = dataRequestsLogRepository;
        this.dataRequestsLogMapper = dataRequestsLogMapper;
        this.agencyRepository = agencyRepository;
        this.citizenRepository = citizenRepository;
    }

    @Override
    public DataRequestsLogResponse save(DataRequestsLogRequest request) {
        DataRequestsLog dataRequestsLog = dataRequestsLogMapper.toEntity(request);
        Agency requestingAgency = agencyRepository.findById(request.getRequestingAgencyId())
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + request.getRequestingAgencyId()));
        dataRequestsLog.setRequestingAgency(requestingAgency);

        if (request.getCitizenId() != 0) {
            Citizen citizen = citizenRepository.findById(request.getCitizenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + request.getCitizenId()));
            dataRequestsLog.setCitizen(citizen);
        }

        DataRequestsLog savedLog = dataRequestsLogRepository.save(dataRequestsLog);
        return dataRequestsLogMapper.toDto(savedLog);
    }

    @Override
    public DataRequestsLogResponse findById(Long id) {
        DataRequestsLog dataRequestsLog = dataRequestsLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DataRequestsLog not found with id: " + id));
        return dataRequestsLogMapper.toDto(dataRequestsLog);
    }

    @Override
    public List<DataRequestsLogResponse> findAll() {
        return dataRequestsLogRepository.findAll().stream()
                .map(dataRequestsLogMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DataRequestsLogResponse update(Long id, DataRequestsLogRequest request) {
        DataRequestsLog existingLog = dataRequestsLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DataRequestsLog not found with id: " + id));
        Agency requestingAgency = agencyRepository.findById(request.getRequestingAgencyId())
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + request.getRequestingAgencyId()));
        existingLog.setRequestingAgency(requestingAgency);

        if (request.getCitizenId() != 0) {
            Citizen citizen = citizenRepository.findById(request.getCitizenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + request.getCitizenId()));
            existingLog.setCitizen(citizen);
        } else {
            existingLog.setCitizen(null);
        }

        existingLog.setDataFieldsRequested(request.getDataFieldsRequested());
        existingLog.setPurposeCode(request.getPurposeCode());

        DataRequestsLog updatedLog = dataRequestsLogRepository.save(existingLog);
        return dataRequestsLogMapper.toDto(updatedLog);
    }

    @Override
    public void deleteById(Long id) {
        if (!dataRequestsLogRepository.existsById(id)) {
            throw new ResourceNotFoundException("DataRequestsLog not found with id: " + id);
        }
        dataRequestsLogRepository.deleteById(id);
    }
}