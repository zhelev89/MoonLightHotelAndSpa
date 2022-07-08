package team2.MoonLightHotelAndSpa.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginRequest;
import team2.MoonLightHotelAndSpa.model.user.User;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    Set<User> findAll();

    User update(Long id, User updatedUser);

    void deleteById(Long id);

    String authenticate(LoginRequest loginRequest);
}
