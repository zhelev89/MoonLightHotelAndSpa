package team2.MoonLightHotelAndSpa.service.screen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenRequest;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.model.screen.ScreenPosition;
import team2.MoonLightHotelAndSpa.repository.ScreenRepository;

import java.util.Optional;

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

    @Test
    public void verifySaveThrowException() {
        Integer[] seats = {1, 2};
        Screen screen = new Screen();
        screen.setId(1L);
        screen.setTitle("Boxing");
        screen.setPosition(ScreenPosition.CENTRAL);
        screen.setImage("URL://image.com");
        screen.setSeats(seats);

        Mockito.when(screenRepository.save(screen))
                .thenThrow(DataIntegrityViolationException.class);

        String message = String.format("Screen on this position:%s is already exists.", screen.getPosition());

        RecordBadRequestException exception = Assertions.assertThrows(RecordBadRequestException.class,
                () -> screenService.save(screen));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyFindById() {
        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder()
                        .id(1L)
                        .build()));

        screenService.findById(1L);
        Mockito.verify(screenRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyFindByIdThrowException() {
        String message = String.format("Screen with id:%s, not found.", 1L);

        RecordNotFoundException exception = Assertions.assertThrows(RecordNotFoundException.class,
                () -> screenService.findById(1L));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyUpdate() {
        Integer[] seats = {1, 2, 3};
        Screen selectedScreen = Screen.builder()
                .id(1L)
                .title("Boxing")
                .image("URL://image.com")
                .position(ScreenPosition.CENTRAL)
                .seats(seats)
                .build();

        Integer[] screenRequestSeats = {1, 2, 3, 4};
        ScreenRequest screenRequest = ScreenRequest.builder()
                .title("Tennis")
                .image("URL://tennis.image.com")
                .position(ScreenPosition.LEFT)
                .seats(screenRequestSeats)
                .build();

        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(selectedScreen));

        screenService.update(1L, screenRequest);

        Assertions.assertEquals(selectedScreen.getTitle(), screenRequest.getTitle());
        Assertions.assertEquals(selectedScreen.getImage(), screenRequest.getImage());
        Assertions.assertEquals(selectedScreen.getPosition(), screenRequest.getPosition());
        Assertions.assertEquals(selectedScreen.getSeats(), screenRequest.getSeats());
    }

    @Test
    public void verifyDelete() {
        screenService.delete(1L);
        Mockito.verify(screenRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void verifyDeleteThrowException() {
        String message = String.format("Screen with id:%s, not found.", 1L);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(screenRepository);

        screenService.delete(1L);

        RecordNotFoundException exception = Assertions.assertThrows(RecordNotFoundException.class,
                () -> screenService.delete(1L));

        Assertions.assertEquals(message, exception.getMessage());
    }
}
