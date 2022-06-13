package team2.MoonLight.Hotel.and.Spa.services;

import team2.MoonLight.Hotel.and.Spa.models.users.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User update(Long id, String newPassword);
}
