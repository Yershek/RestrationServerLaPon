package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.ChangeType;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class DocumentChangesLogResponse {
    private Long id;
    private int documentId;
    private Timestamp changeDate;
    private ChangeType changeType;
    private int agencyId;
    private String reason;
}
