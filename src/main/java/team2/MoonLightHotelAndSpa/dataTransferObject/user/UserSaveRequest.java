package team2.MoonLightHotelAndSpa.dataTransferObject.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequest {

    private String name;
    private String surname;
    @Email
    private String email;
    private String phone;
    private String password;
    private Set<String> roles;
}
