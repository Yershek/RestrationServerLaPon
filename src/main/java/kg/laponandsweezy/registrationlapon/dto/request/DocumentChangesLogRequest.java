package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.ChangeType;
import lombok.Data;

@Data
public class DocumentChangesLogRequest {
    private int documentId;
    private ChangeType changeType;
    private int agencyId;
    private String reason;
}
