package kg.laponandsweezy.registrationlapon.mapper.impl;

import kg.laponandsweezy.registrationlapon.dto.request.RelationshipRequest;
import kg.laponandsweezy.registrationlapon.dto.response.RelationshipResponse;
import kg.laponandsweezy.registrationlapon.entity.Relationship;
import kg.laponandsweezy.registrationlapon.mapper.RelationshipMapper;
import kg.laponandsweezy.registrationlapon.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelationshipMapperImpl implements RelationshipMapper {

    CitizenRepository citizenRepository;

    @Autowired
    public RelationshipMapperImpl(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @Override
    public Relationship toEntity(RelationshipRequest request) {
        if (request == null) {
            return null;
        }
        Relationship relationship = new Relationship();
        relationship.setCitizenA(citizenRepository.findById(request.getCitizenAId()).orElse(null));
        relationship.setCitizenB(citizenRepository.findById(request.getCitizenBId()).orElse(null));
        relationship.setRelationshipType(request.getRelationshipType());
        relationship.setVerified(request.isVerified());
        return relationship;
    }

    @Override
    public RelationshipResponse toDto(Relationship entity) {
        if (entity == null) {
            return null;
        }
        RelationshipResponse response = new RelationshipResponse();
        response.setId(entity.getId());
        response.setCitizenAId(entity.getCitizenA() != null ? entity.getCitizenA().getId() : 0);
        response.setCitizenBId(entity.getCitizenB() != null ? entity.getCitizenB().getId() : 0);
        response.setRelationshipType(entity.getRelationshipType());
        response.setVerified(entity.isVerified());
        return response;
    }
}
