package team2.MoonLightHotelAndSpa.services;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text);
}
