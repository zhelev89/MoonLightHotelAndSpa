package team2.MoonLight.Hotel.and.Spa.services;

import team2.MoonLight.Hotel.and.Spa.models.users.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole save(UserRole userRole);

    UserRole findByRole(String userRole);

    List<UserRole> findAll();
}
