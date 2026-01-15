package kg.laponandsweezy.registrationlapon.mapper;

import kg.laponandsweezy.registrationlapon.dto.request.RelationshipRequest;
import kg.laponandsweezy.registrationlapon.dto.response.RelationshipResponse;
import kg.laponandsweezy.registrationlapon.entity.Relationship;

public interface RelationshipMapper {
    Relationship toEntity(RelationshipRequest request);
    RelationshipResponse toDto(Relationship entity);
}
