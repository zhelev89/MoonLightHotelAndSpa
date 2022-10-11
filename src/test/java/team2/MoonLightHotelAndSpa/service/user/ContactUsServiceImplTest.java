package team2.MoonLightHotelAndSpa.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team2.MoonLightHotelAndSpa.model.contactUsForm.ContactUs;
import team2.MoonLightHotelAndSpa.repository.ContactUsRepository;
import team2.MoonLightHotelAndSpa.service.contactUs.ContactUsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ContactUsServiceImplTest {

    @Mock
    private ContactUsRepository contactUsRepository;

    private ContactUsServiceImpl contactUsService;

    @BeforeEach
    public void setUp() {
        contactUsService = new ContactUsServiceImpl(contactUsRepository);
    }

    @Test
    public void verifySave() {
        ContactUs contactUs = ContactUs.builder()
                .name("Zhivko")
                .email("zhelev89@yahoo.com")
                .phone("0899123123")
                .message("This is my fake number :)")
                .build();

        contactUsService.saveContactUs(contactUs);
        Mockito.verify(contactUsRepository, Mockito.times(1)).save(contactUs);
    }
}
