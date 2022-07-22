package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.contactUs.ContactUs;
import team2.MoonLightHotelAndSpa.repository.ContactUsRepository;
import team2.MoonLightHotelAndSpa.service.ContactUsService;

@Service
@AllArgsConstructor
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepository contactUsRepository;

    public ContactUs saveContactUs(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);
    }
}
