package team2.MoonLightHotelAndSpa.service.user;

import team2.MoonLightHotelAndSpa.model.user.Role;

public interface RoleService {

    Role save(Role role);

    Role findByRole(String userRole);
}
