package team2.MoonLightHotelAndSpa.converter.contactUs;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.contactUsForm.ContactUsRequest;
import team2.MoonLightHotelAndSpa.model.contactUsForm.ContactUs;

@Component
public class ContactUsConverter {

    public ContactUs convert(ContactUsRequest contactUsRequest) {
        return ContactUs.builder()
                .name(contactUsRequest.getName())
                .email(contactUsRequest.getEmail())
                .phone(contactUsRequest.getPhone())
                .message(contactUsRequest.getMessage())
                .build();
    }
}
