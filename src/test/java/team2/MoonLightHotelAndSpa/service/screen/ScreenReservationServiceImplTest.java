package team2.MoonLightHotelAndSpa.service.screen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.ReservationStatus;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.ScreenRepository;
import team2.MoonLightHotelAndSpa.repository.ScreenReservationRepository;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.service.user.UserServiceImpl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void verifySave() {
        Integer[] seats = {1, 2};
        ScreenReservation beforeSave = ScreenReservation.builder()
                .seats(seats)
                .build();

        ScreenReservation afterSave = ScreenReservation.builder()
                .seats(seats)
                .price(seats.length * 10)
                .build();

        Mockito.when(screenReservationRepository.save(beforeSave))
                .thenReturn(afterSave);

        screenReservationService.save(beforeSave);

        Mockito.verify(screenReservationRepository, Mockito.times(1))
                .save(beforeSave);
    }

    @Test
    public void verifyFindByUser() {
        Mockito.when(screenReservationRepository.findByUser(User.builder().build()))
                .thenReturn(List.of(ScreenReservation.builder().build()));

        screenReservationService.findByUser(User.builder().build());

        Mockito.verify(screenReservationRepository, Mockito.times(1))
                .findByUser(User.builder().build());
    }

    @Test
    public void verifyFindFreeSeatsByScreenIdAndDate() {
        String date = "2022-12-12T00:00:00.00Z";
        Integer[] seats = {1, 2, 3};
        Mockito.when(screenReservationRepository.findByScreenIdAndDate(1L, Instant.parse(date)))
                .thenReturn(List.of(ScreenReservation.builder()
                        .seats(seats)
                        .build()));

        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder()
                        .seats(seats)
                        .build()));

        screenReservationService.findFreeSeatsByScreenIdAndDate(1L, date);

        Mockito.verify(screenReservationRepository, Mockito.times(1))
                .findByScreenIdAndDate(1L, Instant.parse(date));

        Mockito.verify(screenRepository, Mockito.times(1))
                .findById(1L);
    }

    @Test
    public void verifyFindByScreenReservationID() {
        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder().build()));

        screenReservationService.findByScreenReservationID(1L);

        Mockito.verify(screenReservationRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyFindByScreenReservationIDThrowException() {
        String message = String.format("Screen Reservation with id:%s, not found.", 1L);

        RecordNotFoundException exception = Assertions.assertThrows(RecordNotFoundException.class,
                () -> screenReservationService.findByScreenReservationID(1L));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyFindAllByScreenId() {
        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder().build()));

        screenReservationService.findAllByScreenId(1L);

        Mockito.verify(screenRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(1))
                .findAllByScreen(Screen.builder().build());
    }

    @Test
    public void verifyFindByScreenIdAndReservationId() {
        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .screen(Screen.builder()
                                .id(1L)
                                .build())
                        .build()));

        screenReservationService.findByScreenIdAndReservationId(1L, 1L);

        Mockito.verify(screenRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyFindByScreenIdAndReservationIdThrowException() {
        String message = "Reservation ID doesn't match with the Screen ID.";

        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .screen(Screen.builder().build())
                        .build()));

        RecordNotFoundException exception = Assertions.assertThrows(RecordNotFoundException.class,
                () -> screenReservationService.findByScreenIdAndReservationId(1L, 1L));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyFindUserIdAndReservationId() {
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(User.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .user(User.builder()
                                .id(1L)
                                .build())
                        .build()));

        screenReservationService.findByUserIdAndReservationId(1L, 1L);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyFindUserIdAndReservationIdThrowException() {
        String message = "Reservation ID doesn't match with the Screen ID.";

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(User.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .user(User.builder()
                                .build())
                        .build()));

        RecordNotFoundException exception = Assertions.assertThrows(RecordNotFoundException.class,
                () -> screenReservationService.findByUserIdAndReservationId(1L, 1L));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyUpdateByScreenIdAndReservationId() {
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(User.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .id(1L)
                        .screen(Screen.builder()
                                .id(1L)
                                .build()).build()));

        Integer[] seats = {1, 2, 3};
        ScreenReservationUpdateRequest updateRequest = ScreenReservationUpdateRequest.builder()
                .date("2022-12-12T00:00:00Z")
                .user(1L)
                .seats(seats)
                .price(50)
                .build();

        ScreenReservation screenReservation =
                screenReservationService.findByScreenIdAndReservationId(1L, 1L);

        User user = userService.findById(1L);

        screenReservationService.updateByScreenIdAndReservationId(1L, 1L, updateRequest);

        Mockito.verify(userRepository, Mockito.times(2)).findById(1L);
        Mockito.verify(screenRepository, Mockito.times(2)).findById(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(2)).findById(1L);

        Assertions.assertEquals(screenReservation.getDate(), Instant.parse(updateRequest.getDate()));
        Assertions.assertEquals(screenReservation.getUser(), user);
        Assertions.assertEquals(screenReservation.getSeats(), updateRequest.getSeats());
        Assertions.assertEquals(screenReservation.getPrice(), updateRequest.getPrice());
    }

    @Test
    public void verifyDeleteByScreenIdAndReservationsId() {
        Mockito.when(screenRepository.findById(1L))
                .thenReturn(Optional.of(Screen.builder()
                        .id(1L)
                        .build()));

        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .screen(Screen.builder()
                                .id(1L)
                                .build())
                        .build()));

        screenReservationService.deleteByScreenIdAndReservationsId(1L, 1L);

        Mockito.verify(screenRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(1))
                .delete(ScreenReservation.builder()
                        .screen(Screen.builder()
                                .id(1L)
                                .build())
                        .build());
    }

    @Test
    public void verifyIsPaid() {
        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .status(ReservationStatus.UNPAID.toString())
                        .build()));

        screenReservationService.isPaid(1L);
        Mockito.verify(screenReservationRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyIsPaidThrowException() {
        String message = "This ScreenReservation is already paid!";
        Mockito.when(screenReservationRepository.findById(1L))
                .thenReturn(Optional.of(ScreenReservation.builder()
                        .status(ReservationStatus.PAID.toString())
                        .build()));

        RecordBadRequestException exception = Assertions.assertThrows(RecordBadRequestException.class,
                () -> screenReservationService.isPaid(1L));

        Assertions.assertEquals(message, exception.getMessage());
    }
}
