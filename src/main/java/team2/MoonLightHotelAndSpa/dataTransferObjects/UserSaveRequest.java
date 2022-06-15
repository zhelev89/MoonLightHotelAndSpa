package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
>>>>>>> bea500f676d4b882bb701d147e9b0ae1841889cb
=======
>>>>>>> main
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequest {

    @NotNull
<<<<<<< HEAD
<<<<<<< HEAD
    private String firstName;

    @NotNull
=======
    @Min(2) @Max(255)
    private String firstName;

    @NotNull
    @Min(2) @Max(255)
>>>>>>> bea500f676d4b882bb701d147e9b0ae1841889cb
=======
    private String firstName;

    @NotNull
>>>>>>> main
    private String lastName;

    @NotNull
    @Email
<<<<<<< HEAD
<<<<<<< HEAD
    private String email;

    @NotNull
=======
    @Min(5) @Max(255)
    private String email;

    @NotNull
    @Max(15)
>>>>>>> bea500f676d4b882bb701d147e9b0ae1841889cb
=======
    private String email;

    @NotNull
>>>>>>> main
    private String phone;

    @NotNull
    private String password;
}
