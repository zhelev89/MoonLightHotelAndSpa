package team2.MoonLightHotelAndSpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class RecordExceptionHandler {

    @ExceptionHandler(RecordBadRequestException.class)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            RecordBadRequestException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RecordNotFoundException ex) {

        RecordResponseException recordResponseException =
                new RecordResponseException(ex.getMessage());

        return new ResponseEntity<>(recordResponseException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format(String.valueOf(constraintViolation.getPropertyPath()),
                        constraintViolation.getMessage())).toList());

        RecordResponseException errorResponse = new RecordResponseException(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
