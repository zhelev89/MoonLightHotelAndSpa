package team2.MoonLightHotelAndSpa.converter;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.contact.ContactUsRequest;
import team2.MoonLightHotelAndSpa.model.contactUs.ContactUs;

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
