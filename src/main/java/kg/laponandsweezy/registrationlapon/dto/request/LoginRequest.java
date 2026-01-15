package kg.laponandsweezy.registrationlapon.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String personalIdNumber;
    private String password;
}
