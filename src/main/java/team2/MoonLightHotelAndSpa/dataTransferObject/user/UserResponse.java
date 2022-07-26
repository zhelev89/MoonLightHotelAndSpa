package team2.MoonLightHotelAndSpa.dataTransferObject.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {

    private long id;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private Set<String> roles;
    private String created;
}
