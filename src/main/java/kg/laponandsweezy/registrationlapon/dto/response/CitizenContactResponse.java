package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.ContactType;
import lombok.Data;

@Data
public class CitizenContactResponse {
    private int id;
    private int citizenId;
    private ContactType contactType;
    private String contactValue;
    private boolean isPrimary;
}
