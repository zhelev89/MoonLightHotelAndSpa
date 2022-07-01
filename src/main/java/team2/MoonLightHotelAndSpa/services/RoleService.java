package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.models.users.Role;

public interface RoleService {

    Role save(Role role);

    Role findByRole(String userRole);
}
