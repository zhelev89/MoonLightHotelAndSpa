package team2.MoonLightHotelAndSpa.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User update(Long id, String newPassword);

    void deleteById(Long id);
}
