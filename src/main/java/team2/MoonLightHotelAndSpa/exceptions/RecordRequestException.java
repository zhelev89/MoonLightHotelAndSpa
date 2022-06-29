package team2.MoonLightHotelAndSpa.exceptions;

import org.springframework.http.HttpStatus;

public class RecordRequestException extends RuntimeException {

    public RecordRequestException(String message) {
        super(message);
    }

    public RecordRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
