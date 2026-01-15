package kg.laponandsweezy.registrationlapon.dto.response;

import kg.laponandsweezy.registrationlapon.enums.MaritalStatusEnums;
import lombok.Data;

@Data
public class MaritalStatusResponse {
    private int id;
    private MaritalStatusEnums name;
}
