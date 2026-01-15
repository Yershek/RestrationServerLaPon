package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.StatusType;
import lombok.Data;
import java.util.Date;

@Data
public class CitizenStatusRequest {
    private int citizenId;
    private StatusType statusType;
    private Date statusDate;
    private String referenceDocument;
}
