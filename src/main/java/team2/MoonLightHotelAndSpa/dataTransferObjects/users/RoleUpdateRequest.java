package team2.MoonLightHotelAndSpa.dataTransferObjects.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleUpdateRequest {

    private String role;
}
