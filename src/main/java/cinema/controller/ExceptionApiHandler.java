package cinema.controller;

import cinema.exception.ExceptionMessage;
import cinema.exception.SeatOutOfBoundsException;
import cinema.exception.SeatOccuredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(SeatOutOfBoundsException.class)
    public ResponseEntity<ExceptionMessage> notFoundException(SeatOutOfBoundsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(exception.getMessage()));
    }

    @ExceptionHandler(SeatOccuredException.class)
    public ResponseEntity<ExceptionMessage> mismatchException(SeatOccuredException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(exception.getMessage()));
    }

}