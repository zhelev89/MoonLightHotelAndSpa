package team2.MoonLightHotelAndSpa.exceptions;

public class RecordRequestException extends RuntimeException {

    public RecordRequestException(String message) {
        super(message);
    }

    public RecordRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
