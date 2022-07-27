package team2.MoonLightHotelAndSpa.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.repository.UserRepository;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void userExistById(long id) {
        Objects.requireNonNull(id);
        if (!userRepository.existsById(id)) {
            throw new RecordNotFoundException("User with id:%s, not exists.");
        }
    }

    public void emailDuplicate(String email) {
        Objects.requireNonNull(email);
        if (!userRepository.existsByEmail(email)) {
            throw new RecordNotFoundException("User with email:%s, not exists.");
        }
    }
}
