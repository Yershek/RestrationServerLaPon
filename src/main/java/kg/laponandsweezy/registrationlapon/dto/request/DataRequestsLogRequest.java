package kg.laponandsweezy.registrationlapon.dto.request;

import lombok.Data;

@Data
public class DataRequestsLogRequest {
    private int requestingAgencyId;
    private int citizenId;
    private String dataFieldsRequested;
    private String purposeCode;
}
