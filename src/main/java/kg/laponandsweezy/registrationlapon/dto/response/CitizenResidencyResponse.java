package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.AddressType;
import lombok.Data;
import java.util.Date;

@Data
public class CitizenResidencyResponse {
    private int id;
    private int citizenId;
    private int addressId;
    private AddressType addressType;
    private Date startDate;
    private Date endDate;
}
