package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exceptions.DuplicateRecordException;
import team2.MoonLightHotelAndSpa.exceptions.NotFoundRecordException;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.repositories.UserRepository;
import team2.MoonLightHotelAndSpa.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        try {
            Objects.requireNonNull(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateRecordException("User with this email or phone is already exist.");
        }
    }

    public User findById(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundRecordException(
                        String.format("User with id:%s, not found.", id)));
    }

    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    public User update(Long id, String newPassword) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newPassword);

        User foundUser = findById(id);
        foundUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return foundUser;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
