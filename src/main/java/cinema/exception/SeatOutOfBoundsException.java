package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatOutOfBoundsException extends RuntimeException {
    private static final String MESSAGE = "The number of a row or a column is out of bounds!";

    public SeatOutOfBoundsException() {
        super(MESSAGE);
    }
}
