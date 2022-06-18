package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.models.users.Role;

import java.util.Set;

@Data
@Builder
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<RoleResponse> roles;
}
