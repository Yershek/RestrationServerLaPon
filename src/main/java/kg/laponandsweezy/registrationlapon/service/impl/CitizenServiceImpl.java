package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.CitizenRequest;
import kg.laponandsweezy.registrationlapon.dto.response.CitizenResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.exception.CitizenNotFoundException;
import kg.laponandsweezy.registrationlapon.exception.DuplicateRecordException;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.CitizenMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.security.UserDetailsImpl;
import kg.laponandsweezy.registrationlapon.service.CitizenService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;
    private final PasswordEncoder passwordEncoder;

    public CitizenServiceImpl(CitizenRepository citizenRepository, CitizenMapper citizenMapper, @Lazy PasswordEncoder passwordEncoder) {
        this.citizenRepository = citizenRepository;
        this.citizenMapper = citizenMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CitizenResponse save(CitizenRequest request) {
        if (citizenRepository.findCitizenByPersonalIdNumber(request.getPersonalIdNumber()).isPresent()) {
            throw new DuplicateRecordException("Citizen with PIN " + request.getPersonalIdNumber() + " already exists");
        }
        Citizen citizen = citizenMapper.toEntity(request);
        citizen.setPassword(passwordEncoder.encode(request.getPassword()));
        Citizen savedCitizen = citizenRepository.save(citizen);
        return citizenMapper.toDto(savedCitizen);
    }

    @Override
    public CitizenResponse findById(int id) {
        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + id));
        return citizenMapper.toDto(citizen);
    }

    @Override
    public List<CitizenResponse> findAll() {
        return citizenRepository.findAll().stream()
                .map(citizenMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CitizenResponse update(int id, CitizenRequest request) {
        Citizen existingCitizen = citizenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with id: " + id));
        
        // Check if PIN is being changed to one that already exists
        if (!existingCitizen.getPersonalIdNumber().equals(request.getPersonalIdNumber()) &&
            citizenRepository.findCitizenByPersonalIdNumber(request.getPersonalIdNumber()).isPresent()) {
             throw new DuplicateRecordException("Citizen with PIN " + request.getPersonalIdNumber() + " already exists");
        }

        existingCitizen.setPersonalIdNumber(request.getPersonalIdNumber());
        existingCitizen.setLastName(request.getLastName());
        existingCitizen.setFirstName(request.getFirstName());
        existingCitizen.setMiddleName(request.getMiddleName());
        existingCitizen.setDateOfBirth(request.getDateOfBirth());
        existingCitizen.setGender(request.getGender());
        existingCitizen.setPlaceOfBirth(request.getPlaceOfBirth());
        existingCitizen.setNationality(request.getNationality());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            existingCitizen.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Citizen updatedCitizen = citizenRepository.save(existingCitizen);
        return citizenMapper.toDto(updatedCitizen);
    }

    @Override
    public void deleteById(int id) {
        if (!citizenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Citizen not found with id: " + id);
        }
        citizenRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Citizen citizen = citizenRepository.findCitizenByPersonalIdNumber(username).orElseThrow(() -> new CitizenNotFoundException(
                String.format("User '%s' not found", username)
        ));

        return UserDetailsImpl.build(citizen);
    }
}
