package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.Gender;
import lombok.Data;
import java.util.Date;

@Data
public class CitizenRequest {
    private String personalIdNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date dateOfBirth;
    private Gender gender;
    private String placeOfBirth;
    private String nationality;
    private String password;
}
