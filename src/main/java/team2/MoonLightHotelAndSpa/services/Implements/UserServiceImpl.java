package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exceptions.DuplicateRecordException;
import team2.MoonLightHotelAndSpa.exceptions.NotFoundRecordException;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.models.users.Role;
import team2.MoonLightHotelAndSpa.repositories.UserRepository;
import team2.MoonLightHotelAndSpa.services.RoleService;
import team2.MoonLightHotelAndSpa.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        try {
            Objects.requireNonNull(user);
            Role customer = roleService.findByRole("Customer");
            Set<Role> customerRoles = new HashSet<>();
            customerRoles.add(customer);
            user.setRoles(customerRoles);
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(Long id, String newPassword) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(newPassword);

        User foundedUser = findById(id);
        foundedUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return foundedUser;
    }
}
