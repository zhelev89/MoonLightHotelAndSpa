package team2.MoonLightHotelAndSpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdDoesNotMatchException extends RuntimeException {

    public IdDoesNotMatchException(String message) {
        super(message);
    }

    public IdDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
