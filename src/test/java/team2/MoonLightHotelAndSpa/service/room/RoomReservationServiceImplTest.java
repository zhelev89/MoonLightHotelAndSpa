package team2.MoonLightHotelAndSpa.service.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.user.Role;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.service.room.RoomReservationServiceImpl;
import team2.MoonLightHotelAndSpa.service.user.UserService;
import team2.MoonLightHotelAndSpa.service.user.UserServiceImpl;
import team2.MoonLightHotelAndSpa.validator.RoomReservationValidator;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomReservationServiceImplTest {

    private RoomReservationServiceImpl roomReservationService;

    @Mock
    private RoomReservationRepository roomReservationRepository;

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RoomReservationValidator roomReservationValidator;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
        roomReservationService = new RoomReservationServiceImpl(userService, roomReservationRepository);
    }

    @Test
    public void verifySave() {
        RoomReservation roomReservation = RoomReservation.builder().build();
        roomReservationService.save(roomReservation);
        verify(roomReservationRepository, times(1)).save(roomReservation);
    }

    @Test
    public void verifyFindAllByUserId() {
        User user = User.builder()
                .id(1L)
                .name("Georgi")
                .surname("Georgiev")
                .phone("0888888888")
                .email("email@gmail.com")
                .password("12345678")
                .roles(Set.of(Role.builder().build()))
                .created(Instant.now())
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        roomReservationService.findAllByUserId(user.getId());
        verify(roomReservationRepository, times(1)).findAllByUser(user);
    }

    @Test
    public void verifyFindAll() {
        when(roomReservationRepository.findAll()).thenReturn(List.of(RoomReservation.builder().build()));
        roomReservationService.findAll();
        verify(roomReservationRepository, times(1)).findAll();
    }

    @Test
    public void verifyCalculateDays() {
        String startDateString  = "2022-07-15T18:35:24.00Z";
        String endDateString = "2022-08-01T18:35:24.00Z";
        Instant startDate = Instant.parse(startDateString);
        Instant endDate = Instant.parse(endDateString);
        roomReservationService.calculateDays(startDate, endDate);
        boolean isTrue = startDate.isBefore(endDate);
        assertTrue(isTrue);
    }

    @Test
    public void verifyCalculateDaysThrowsException() {
        String endDateString  = "2022-07-15T18:35:24.00Z";
        String startDateString = "2022-08-01T18:35:24.00Z";
        Instant startDate = Instant.parse(startDateString);
        Instant endDate = Instant.parse(endDateString);
        String message = "Days should be more than 0";
        RecordBadRequestException recordNotFoundException = assertThrows(RecordBadRequestException.class, () -> {
            roomReservationService.calculateDays(startDate, endDate);
        });
        assertEquals(message, recordNotFoundException.getMessage());
    }
}
