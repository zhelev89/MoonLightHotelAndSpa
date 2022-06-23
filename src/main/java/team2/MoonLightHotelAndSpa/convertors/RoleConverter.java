package team2.MoonLightHotelAndSpa.convertors;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.RoleUpdateRequest;
import team2.MoonLightHotelAndSpa.models.users.Role;

@Component
public class RoleConverter {

    public Role convert(RoleUpdateRequest roleUpdateRequest) {
        return Role.builder()
                .role(roleUpdateRequest.getRole())
                .build();
    }
}
