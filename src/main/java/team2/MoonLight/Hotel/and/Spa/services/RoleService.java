package team2.MoonLight.Hotel.and.Spa.services;

import team2.MoonLight.Hotel.and.Spa.models.users.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role findByRole(String userRole);

    List<Role> findAll();
}
