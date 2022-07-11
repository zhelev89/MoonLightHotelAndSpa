package team2.MoonLightHotelAndSpa.dataTransferObject.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private UserResponse user;
}
