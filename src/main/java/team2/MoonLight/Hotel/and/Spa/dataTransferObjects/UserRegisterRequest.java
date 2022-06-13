package team2.MoonLight.Hotel.and.Spa.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull
    @Min(2) @Max(255)
    private String firstName;

    @NotNull
    @Min(2) @Max(255)
    private String lastName;

    @NotNull
    @Email
    @Min(5) @Max(255)
    private String email;

    @NotNull
    @Max(15)
    private String phone;

    @NotNull
    private String password;
}
