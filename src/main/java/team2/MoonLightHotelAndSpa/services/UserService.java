package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.Set;

public interface UserService {

    User save(User user);

    User findById(Long id);

    Set<User> findAll();

    User update(Long id, String newPassword);

    void deleteById(Long id);
}
