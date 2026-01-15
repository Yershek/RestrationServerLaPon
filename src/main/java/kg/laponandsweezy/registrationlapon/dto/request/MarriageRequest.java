package kg.laponandsweezy.registrationlapon.dto.request;

import lombok.Data;
import java.util.Date;

@Data
public class MarriageRequest {
    private int husbandId;
    private int wifeId;
    private int maritalStatusId;
    private Date marriageDate;
    private String registrationAuthority;
    private String documentSeriesNumber;
    private boolean isActive;
    private Date divorceDate;
}
