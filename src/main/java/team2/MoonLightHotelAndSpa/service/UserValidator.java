package team2.MoonLightHotelAndSpa.service;

public interface UserValidator {
    boolean isUserExists(Long id);

    boolean isEmailExists(String email);
}
