package team2.MoonLightHotelAndSpa.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.util.Set;

public interface UserService extends UserDetailsService {
    User save(User user);

    User findById(long id);

    User findByEmail(String email);

    Set<User> findAll();

    User update(long id, User updatedUser);

    void deleteById(long id);

    User resetPassword(String newPassword, String currentPassword, String email);
}
