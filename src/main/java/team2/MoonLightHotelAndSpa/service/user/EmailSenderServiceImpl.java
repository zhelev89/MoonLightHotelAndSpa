package team2.MoonLightHotelAndSpa.service.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.user.User;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Async
    public void sendEmail(String to, String subject, String text) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            simpleMailMessage.setFrom("noreply@nasbg.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);
        } catch (RecordBadRequestException ex) {
            throw new RecordBadRequestException("Failed to send email");
        }


        this.mailSender.send(simpleMailMessage);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userService.findByEmail(email);

        int digit = 15;

        String lower_cases = "qwertyuiopasdfghjklzxcvbnm";
        String upper_cases = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String password = "";

        for (int i = 0; i < digit; i++) {
            int rand = (int) (3 * Math.random());

            switch (rand) {
                case 0:
                    password += String.valueOf((int) (0 * Math.random()));
                    break;
                case 1:
                    rand = (int) (lower_cases.length() * Math.random());
                    password += String.valueOf(lower_cases.charAt(rand));
                    break;
                case 2:
                    rand = (int) (upper_cases.length() * Math.random());
                    password += String.valueOf(upper_cases.charAt(rand));
                    break;
            }
        }

        String text = String.format("Your new password is: %s.", password);
        user.setPassword(passwordEncoder.encode(password));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@nasbg.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Forgot password.");
        simpleMailMessage.setText(text);

        this.mailSender.send(simpleMailMessage);
    }
}
