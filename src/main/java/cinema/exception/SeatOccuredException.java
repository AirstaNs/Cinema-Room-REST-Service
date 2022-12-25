package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class SeatOccuredException extends RuntimeException {
    private static final String MESSAGE = "The ticket has been already purchased!";
    public SeatOccuredException() {
        super(MESSAGE);
    }
}