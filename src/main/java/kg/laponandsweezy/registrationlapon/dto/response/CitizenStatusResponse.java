package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.StatusType;
import lombok.Data;
import java.util.Date;

@Data
public class CitizenStatusResponse {
    private int id;
    private int citizenId;
    private StatusType statusType;
    private Date statusDate;
    private String referenceDocument;
}
