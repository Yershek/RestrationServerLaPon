package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenContactRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenContactResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.CitizenContact;
import kg.laponandsweezy.registrationlapon.mapper.CitizenContactMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenContactRepository;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.service.CitizenContactService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenContactServiceImpl implements CitizenContactService {

    private final CitizenContactRepository citizenContactRepository;
    private final CitizenContactMapper citizenContactMapper;
    private final CitizenRepository citizenRepository;

    public CitizenContactServiceImpl(CitizenContactRepository citizenContactRepository,
                                     CitizenContactMapper citizenContactMapper,
                                     CitizenRepository citizenRepository) {
        this.citizenContactRepository = citizenContactRepository;
        this.citizenContactMapper = citizenContactMapper;
        this.citizenRepository = citizenRepository;
    }

    @Override
    public CitizenContactResponse save(CitizenContactRequest request) {
        CitizenContact citizenContact = citizenContactMapper.toEntity(request);
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + request.getCitizenId()));
        citizenContact.setCitizen(citizen);

        CitizenContact savedContact = citizenContactRepository.save(citizenContact);
        return citizenContactMapper.toDto(savedContact);
    }

    @Override
    public CitizenContactResponse findById(int id) {
        CitizenContact citizenContact = citizenContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CitizenContact not found with id: " + id));
        return citizenContactMapper.toDto(citizenContact);
    }

    @Override
    public List<CitizenContactResponse> findAll() {
        return citizenContactRepository.findAll().stream()
                .map(citizenContactMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CitizenContactResponse update(int id, CitizenContactRequest request) {
        CitizenContact existingContact = citizenContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CitizenContact not found with id: " + id));
        Citizen citizen = citizenRepository.findById(request.getCitizenId())
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + request.getCitizenId()));
        existingContact.setCitizen(citizen);

        existingContact.setContactType(request.getContactType());
        existingContact.setContactValue(request.getContactValue());
        existingContact.setPrimary(request.isPrimary());

        CitizenContact updatedContact = citizenContactRepository.save(existingContact);
        return citizenContactMapper.toDto(updatedContact);
    }

    @Override
    public void deleteById(int id) {
        citizenContactRepository.deleteById(id);
    }
}
