package kg.laponandsweezy.registrationlapon.dto.request;

import lombok.Data;
import java.util.Date;

@Data
public class DocumentRequest {
    private int citizenId;
    private int documentTypeId;
    private String series;
    private String number;
    private Date issueDate;
    private Date expiryDate;
    private String issuingAuthority;
    private boolean isActive;
}
