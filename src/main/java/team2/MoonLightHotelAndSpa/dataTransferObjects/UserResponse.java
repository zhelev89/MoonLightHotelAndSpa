package team2.MoonLightHotelAndSpa.dataTransferObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
