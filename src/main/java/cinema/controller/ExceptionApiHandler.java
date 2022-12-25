package cinema.controller;

import cinema.exception.*;
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
    public ResponseEntity<ExceptionMessage> occurredException(SeatOccuredException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(exception.getMessage()));
    }
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ExceptionMessage> notFoundException(SeatNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(exception.getMessage()));
    }
    @ExceptionHandler(EmptyTicketsException.class)
    public ResponseEntity<ExceptionMessage> emptyTicketsException(EmptyTicketsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(exception.getMessage()));
    }
}