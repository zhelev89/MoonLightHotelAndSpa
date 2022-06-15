package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User update(Long id, String newPassword);
}
