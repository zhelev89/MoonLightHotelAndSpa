package team2.MoonLight.Hotel.and.Spa.exceptions;

import java.util.function.Supplier;

public class NotFoundRecordException extends RuntimeException {
    public NotFoundRecordException(String message) {
        super(message);
    }
}
