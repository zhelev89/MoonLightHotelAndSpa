package team2.MoonLightHotelAndSpa.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.Set;

public interface UserService extends UserDetailsService {

    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    Set<User> findAll();

    User update(Long id, User updatedUser);

    void deleteById(Long id);

    User changePassword(String newPassword, String currentPassword, String email);

}
