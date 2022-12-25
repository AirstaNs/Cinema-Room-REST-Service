package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmptyTicketsException extends RuntimeException  {

        private static final String MESSAGE = "Wrong token!";

        public EmptyTicketsException() {
            super(MESSAGE);
        }
}
