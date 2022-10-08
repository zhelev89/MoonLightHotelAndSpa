package team2.MoonLightHotelAndSpa.service.user;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text);

    void forgotPassword(String email);
}
