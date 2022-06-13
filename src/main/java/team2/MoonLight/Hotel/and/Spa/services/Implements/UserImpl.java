package team2.MoonLight.Hotel.and.Spa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.MoonLight.Hotel.and.Spa.exceptions.DuplicateRecordException;
import team2.MoonLight.Hotel.and.Spa.models.users.User;
import team2.MoonLight.Hotel.and.Spa.models.users.UserRole;
import team2.MoonLight.Hotel.and.Spa.repositories.UserRepository;
import team2.MoonLight.Hotel.and.Spa.services.UserRoleService;
import team2.MoonLight.Hotel.and.Spa.services.UserService;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        try {
            Objects.requireNonNull(user);
            UserRole customer = userRoleService.findByRole("Customer");
            user.setUserRole(customer);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateRecordException("User with this email or phone is already exist.");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
