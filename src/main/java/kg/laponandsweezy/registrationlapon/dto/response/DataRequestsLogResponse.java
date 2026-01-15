package kg.laponandsweezy.registrationlapon.dto.response;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class DataRequestsLogResponse {
    private Long id;
    private int requestingAgencyId;
    private int citizenId;
    private Timestamp requestTimestamp;
    private String dataFieldsRequested;
    private String purposeCode;
}
