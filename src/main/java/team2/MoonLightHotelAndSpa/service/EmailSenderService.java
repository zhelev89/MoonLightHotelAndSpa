package team2.MoonLightHotelAndSpa.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text);
}
