package team2.MoonLightHotelAndSpa.dataTransferObjects.users;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private Set<String> roles;
    private String created;
}
