package team2.MoonLightHotelAndSpa.exceptions;

import java.util.function.Supplier;

public class NotFoundRecordException extends RuntimeException {
    public NotFoundRecordException(String message) {
        super(message);
    }
}
