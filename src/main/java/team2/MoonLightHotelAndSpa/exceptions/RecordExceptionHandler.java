package team2.MoonLightHotelAndSpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecordExceptionHandler {

    @ExceptionHandler(value = {RecordBadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(RecordBadRequestException ex) {

        RecordResponseException recordResponseException =
                new RecordResponseException(ex.getMessage());

        return new ResponseEntity<>(recordResponseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RecordNotFoundException ex) {

        RecordResponseException recordResponseException =
                new RecordResponseException(ex.getMessage());

        return new ResponseEntity<>(recordResponseException, HttpStatus.NOT_FOUND);
    }

}
