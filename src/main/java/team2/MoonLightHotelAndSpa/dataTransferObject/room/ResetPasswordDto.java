package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.Getter;

@Getter
public class ResetPasswordDto {

    private String newPassword;
    private String currentPassword;
    private String email;
}
