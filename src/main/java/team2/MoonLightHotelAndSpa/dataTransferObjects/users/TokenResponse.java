package team2.MoonLightHotelAndSpa.dataTransferObjects.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenResponse {

    private String token;
}
