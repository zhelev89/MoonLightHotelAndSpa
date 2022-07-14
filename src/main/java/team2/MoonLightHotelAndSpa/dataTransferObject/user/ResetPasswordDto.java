package team2.MoonLightHotelAndSpa.dataTransferObject.user;

import lombok.Getter;

@Getter
public class ResetPasswordDto {

    private String newPassword;
    private String currentPassword;
    private String email;
}
