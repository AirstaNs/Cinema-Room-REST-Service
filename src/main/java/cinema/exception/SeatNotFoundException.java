package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatNotFoundException extends RuntimeException {
    private static final String MESSAGE = "No place found";

    public SeatNotFoundException() {
        super(MESSAGE);
    }
}
