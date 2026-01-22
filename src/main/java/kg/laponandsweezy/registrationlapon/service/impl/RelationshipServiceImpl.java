package kg.laponandsweezy.registrationlapon.service.impl;

import kg.laponandsweezy.registrationlapon.dto.request.RelationshipRequest;
import kg.laponandsweezy.registrationlapon.dto.response.RelationshipResponse;
import kg.laponandsweezy.registrationlapon.entity.Citizen;
import kg.laponandsweezy.registrationlapon.entity.Relationship;
import kg.laponandsweezy.registrationlapon.exception.ResourceNotFoundException;
import kg.laponandsweezy.registrationlapon.mapper.RelationshipMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import kg.laponandsweezy.registrationlapon.repository.RelationshipRepository;
import kg.laponandsweezy.registrationlapon.service.RelationshipService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    private final RelationshipRepository relationshipRepository;
    private final RelationshipMapper relationshipMapper;
    private final CitizenRepository citizenRepository;

    public RelationshipServiceImpl(RelationshipRepository relationshipRepository,
                                   RelationshipMapper relationshipMapper,
                                   CitizenRepository citizenRepository) {
        this.relationshipRepository = relationshipRepository;
        this.relationshipMapper = relationshipMapper;
        this.citizenRepository = citizenRepository;
    }

    @Override
    public RelationshipResponse save(RelationshipRequest request) {
        Relationship relationship = relationshipMapper.toEntity(request);
        Citizen citizenA = citizenRepository.findById(request.getCitizenAId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen A not found with id: " + request.getCitizenAId()));
        relationship.setCitizenA(citizenA);

        Citizen citizenB = citizenRepository.findById(request.getCitizenBId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen B not found with id: " + request.getCitizenBId()));
        relationship.setCitizenB(citizenB);

        Relationship savedRelationship = relationshipRepository.save(relationship);
        return relationshipMapper.toDto(savedRelationship);
    }

    @Override
    public RelationshipResponse findById(int id) {
        Relationship relationship = relationshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relationship not found with id: " + id));
        return relationshipMapper.toDto(relationship);
    }

    @Override
    public List<RelationshipResponse> findAll() {
        return relationshipRepository.findAll().stream()
                .map(relationshipMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RelationshipResponse update(int id, RelationshipRequest request) {
        Relationship existingRelationship = relationshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relationship not found with id: " + id));
        Citizen citizenA = citizenRepository.findById(request.getCitizenAId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen A not found with id: " + request.getCitizenAId()));
        existingRelationship.setCitizenA(citizenA);

        Citizen citizenB = citizenRepository.findById(request.getCitizenBId())
                .orElseThrow(() -> new ResourceNotFoundException("Citizen B not found with id: " + request.getCitizenBId()));
        existingRelationship.setCitizenB(citizenB);

        existingRelationship.setRelationshipType(request.getRelationshipType());
        existingRelationship.setVerified(request.isVerified());

        Relationship updatedRelationship = relationshipRepository.save(existingRelationship);
        return relationshipMapper.toDto(updatedRelationship);
    }

    @Override
    public void deleteById(int id) {
        if (!relationshipRepository.existsById(id)) {
            throw new ResourceNotFoundException("Relationship not found with id: " + id);
        }
        relationshipRepository.deleteById(id);
    }
}