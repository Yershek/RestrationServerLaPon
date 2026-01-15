package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.RelationshipType;
import lombok.Data;

@Data
public class RelationshipResponse {
    private int id;
    private int citizenAId;
    private int citizenBId;
    private RelationshipType relationshipType;
    private boolean isVerified;
}
