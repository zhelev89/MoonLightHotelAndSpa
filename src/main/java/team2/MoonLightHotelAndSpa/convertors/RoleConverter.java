package team2.MoonLightHotelAndSpa.convertors;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoleResponse;
import team2.MoonLightHotelAndSpa.models.users.Role;

@Component
public class RoleConverter {

    public RoleResponse convert(Role role) {
        return RoleResponse.builder()
                .role(role.getRole())
                .build();
    }
}
