package team2.MoonLightHotelAndSpa.service.contactUs;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.contactUsForm.ContactUs;
import team2.MoonLightHotelAndSpa.repository.ContactUsRepository;

@Service
@AllArgsConstructor
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;

    public ContactUs saveContactUs(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);
    }
}
