package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.ContactType;
import lombok.Data;

@Data
public class CitizenContactRequest {
    private int citizenId;
    private ContactType contactType;
    private String contactValue;
    private boolean isPrimary;
}
