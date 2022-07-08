package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginRequest;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.security.JwtTokenUtil;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.exceptions.PasswordNotMatchingException;
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
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public User save(User user) {
        try {
            Objects.requireNonNull(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordBadRequestException("User with this email or phone is already exist.", ex.getCause());
        }
    }

    public User findById(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("User with id:%s, not found.", id)));
    }

    public User findByEmail(String email) {
        Objects.requireNonNull(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("User with email:%s, not found.", email, HttpStatus.NOT_FOUND)));
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
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("User with id:%s, not found.", id));
        }
    }

    @Override
    public User changePassword(String newPassword, String currentPassword, String email) {
        User user = findByEmail(email);
        if(bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }else {
            throw new PasswordNotMatchingException("Your old password does not match");
        }
        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public String authenticate(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User user = (User) authentication.getDetails();

        return jwtTokenUtil.generateToken(user);
    }
}