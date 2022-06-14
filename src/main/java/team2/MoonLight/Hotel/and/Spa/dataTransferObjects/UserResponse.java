package team2.MoonLight.Hotel.and.Spa.dataTransferObjects;

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
