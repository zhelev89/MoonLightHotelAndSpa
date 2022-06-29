package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exceptions.RecordRequestException;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.repositories.UserRepository;
import team2.MoonLightHotelAndSpa.services.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        try {
            Objects.requireNonNull(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordRequestException("User with this email or phone is already exist.", ex.getCause());
        }
    }

    public User findById(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordRequestException(
                        String.format("User with id:%s, not found.", id)));
    }

    public User findByEmail(String email) {
        Objects.requireNonNull(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordRequestException(
                        String.format("User with email:%s, not found.", email)));
    }

    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    public User update(Long id, User updatedUser) {
        Objects.requireNonNull(id);

        User user = findById(id);
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setPhone(updatedUser.getPhone());
        user.setRoles(updatedUser.getRoles());

        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}