package kg.laponandsweezy.registrationlapon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends BaseException {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
