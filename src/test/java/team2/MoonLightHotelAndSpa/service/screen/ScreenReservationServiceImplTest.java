package team2.MoonLightHotelAndSpa.service.screen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.repository.ScreenRepository;
import team2.MoonLightHotelAndSpa.repository.ScreenReservationRepository;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.service.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ScreenReservationServiceImplTest {

    @Mock
    private ScreenReservationRepository screenReservationRepository;
    private ScreenReservationServiceImpl screenReservationService;

    @Mock
    private ScreenRepository screenRepository;
    private ScreenServiceImpl screenService;

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl userService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        screenService = new ScreenServiceImpl(screenRepository);
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
        screenReservationService = new ScreenReservationServiceImpl(screenReservationRepository,
                screenService, userService);
    }
}
