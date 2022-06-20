package team2.MoonLightHotelAndSpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundRecordException extends RuntimeException {
    public NotFoundRecordException(String message) {
        super(message);
    }
}
