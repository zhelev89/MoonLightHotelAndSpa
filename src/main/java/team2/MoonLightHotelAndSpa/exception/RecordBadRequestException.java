package team2.MoonLightHotelAndSpa.exception;

public class RecordBadRequestException extends RuntimeException {

    public RecordBadRequestException(String message) {
        super(message);
    }

    public RecordBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
