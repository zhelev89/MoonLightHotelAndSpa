package team2.MoonLightHotelAndSpa.service.screen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.model.screen.ScreenPosition;
import team2.MoonLightHotelAndSpa.repository.ScreenRepository;

import javax.persistence.criteria.CriteriaBuilder;

@ExtendWith(MockitoExtension.class)
public class ScreenServiceImplTest {

    @Mock
    private ScreenRepository screenRepository;
    private ScreenServiceImpl screenService;

    @BeforeEach
    public void setUp() {
        screenService = new ScreenServiceImpl(screenRepository);
    }

    @Test
    public void verifySave() {
        screenService.save(Screen.builder().build());
        Mockito.verify(screenRepository, Mockito.times(1)).save(Screen.builder().build());
    }

//    @Test
//    public void verifySaveThrowException() {
//        Integer[] seats = {1, 2};
//
//        Screen screen = new Screen();
//        screen.setId(1L);
//        screen.setTitle("Boxing");
//        screen.setPosition(ScreenPosition.CENTRAL);
//        screen.setImage("URL://image.com");
//        screen.setSeats(seats);
//
//        String message = String.format("Screen on this position:%s is already exists.", screen.getPosition());
//
//        RecordBadRequestException exception = Assertions.assertThrows(RecordBadRequestException.class,
//                () -> screenService.save(screen));
//
//        Assertions.assertEquals(message, exception.getMessage());
//    }
}
