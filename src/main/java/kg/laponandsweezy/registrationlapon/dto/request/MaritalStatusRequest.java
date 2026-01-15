package kg.laponandsweezy.registrationlapon.dto.request;

import kg.laponandsweezy.registrationlapon.enums.MaritalStatusEnums;
import lombok.Data;

@Data
public class MaritalStatusRequest {
    private MaritalStatusEnums name;
}
