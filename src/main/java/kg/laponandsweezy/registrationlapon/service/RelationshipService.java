package kg.laponandsweezy.registrationlapon.service;

import kg.laponandsweezy.registrationlapon.dto.request.RelationshipRequest;
import kg.laponandsweezy.registrationlapon.dto.response.RelationshipResponse;
import java.util.List;

public interface RelationshipService {
    RelationshipResponse save(RelationshipRequest request);
    RelationshipResponse findById(int id);
    List<RelationshipResponse> findAll();
    RelationshipResponse update(int id, RelationshipRequest request);
    void deleteById(int id);
}
