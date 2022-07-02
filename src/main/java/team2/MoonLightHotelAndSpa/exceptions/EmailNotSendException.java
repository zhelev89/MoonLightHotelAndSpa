package team2.MoonLightHotelAndSpa.exceptions;

public class EmailNotSendException extends RuntimeException{

    public EmailNotSendException(String message) {
        super(message);
    }

    public EmailNotSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
