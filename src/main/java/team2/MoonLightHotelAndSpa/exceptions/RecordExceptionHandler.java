package team2.MoonLightHotelAndSpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecordExceptionHandler {

    @ExceptionHandler(value = {RecordRequestException.class})
    public ResponseEntity<Object> handleException(RecordRequestException ex) {

        RecordResponseException recordResponseException =
                new RecordResponseException(ex.getMessage());

        return new ResponseEntity<>(recordResponseException, HttpStatus.BAD_REQUEST);
    }

}
