package team2.MoonLightHotelAndSpa.dataTransferObject.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserUpdateRequest {

    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private Set<String> roles;
}
