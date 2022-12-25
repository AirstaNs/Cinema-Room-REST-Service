package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class LoginWrongException extends RuntimeException {

    private static final String MESSAGE = "The password is wrong!";

    public LoginWrongException() {
        super(MESSAGE);
    }
}
