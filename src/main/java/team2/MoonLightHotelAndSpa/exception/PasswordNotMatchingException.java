package team2.MoonLightHotelAndSpa.exception;

public class PasswordNotMatchingException extends RuntimeException{

    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
