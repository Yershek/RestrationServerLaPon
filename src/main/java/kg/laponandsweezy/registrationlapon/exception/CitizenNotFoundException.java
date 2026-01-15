package kg.laponandsweezy.registrationlapon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CitizenNotFoundException extends BaseException {
    public CitizenNotFoundException(String message) {
        super(message);
    }
}
