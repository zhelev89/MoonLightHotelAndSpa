package team2.MoonLightHotelAndSpa.dataTransferObjects.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.time.Instant;
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
