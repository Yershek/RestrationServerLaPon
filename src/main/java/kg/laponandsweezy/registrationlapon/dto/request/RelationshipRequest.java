package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.RelationshipType;
import lombok.Data;

@Data
public class RelationshipRequest {
    private int citizenAId;
    private int citizenBId;
    private RelationshipType relationshipType;
    private boolean isVerified;
}
