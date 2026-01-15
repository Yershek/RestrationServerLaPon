package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.AddressType;
import lombok.Data;
import java.util.Date;

@Data
public class CitizenResidencyRequest {
    private int citizenId;
    private int addressId;
    private AddressType addressType;
    private Date startDate;
    private Date endDate;
}
