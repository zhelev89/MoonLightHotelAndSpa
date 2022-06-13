package team2.MoonLight.Hotel.and.Spa.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
