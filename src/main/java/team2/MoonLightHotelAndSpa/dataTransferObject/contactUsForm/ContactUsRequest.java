package team2.MoonLightHotelAndSpa.dataTransferObject.contactUsForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsRequest {

    private String name;
    @Email
    private String email;
    private String phone;
    private String message;
}
