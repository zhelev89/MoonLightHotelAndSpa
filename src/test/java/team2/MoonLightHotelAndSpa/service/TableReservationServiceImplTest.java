package team2.MoonLightHotelAndSpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.TableRepository;
import team2.MoonLightHotelAndSpa.repository.TableReservationRepository;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.service.table.TableReservationServiceImpl;
import team2.MoonLightHotelAndSpa.service.table.TableServiceImpl;
import team2.MoonLightHotelAndSpa.service.user.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TableReservationServiceImplTest {

    @Mock
    private TableReservationRepository tableReservationRepository;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private TableReservationServiceImpl tableReservationService;
    private TableServiceImpl tableService;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        tableService = new TableServiceImpl(tableRepository);
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
        tableReservationService = new TableReservationServiceImpl(tableReservationRepository, tableService, userService);
    }

    @Test
    public void verifySave() {
        TableReservation tableReservation = new TableReservation();
        tableReservationService.save(tableReservation);
        Mockito.verify(tableReservationRepository, Mockito.times(1)).save(tableReservation);
    }

    @Test
    public void verifyFindById() {
        long id = 1L;
        Mockito.when(tableReservationRepository.findById(id))
                .thenReturn(Optional.of(TableReservation.builder()
                        .id(id)
                        .build()));
        tableReservationService.findById(id);
        Mockito.verify(tableReservationRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void verifyFindByIdThrowException() {
        long id = 1;
        String message = String.format("Table reservation with id:%s, not found", id);
        RecordNotFoundException exception = Assertions.assertThrows(RecordNotFoundException.class,
                () -> tableReservationService.findById(id));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyFindAllByTable() {
        long id = 1;
        Mockito.when(tableReservationService.findAllByTable(id))
                .thenReturn(List.of(TableReservation.builder().build()));
        Mockito.verify(tableReservationRepository.findByTable(
                Table.builder().id(id).build()), Mockito.times(1));
    }

    @Test
    public void verifyFindByTableIdAndReservationId() {
        Mockito.when(tableRepository.findById(1L))
                .thenReturn(Optional.of(Table.builder()
                        .id(1L)
                        .build()));

        Mockito.when(tableReservationRepository.findById(1L))
                .thenReturn(Optional.of(TableReservation.builder()
                        .id(1L)
                        .table(Table.builder().id(1L)
                                .build())
                        .build()));

        tableReservationService.findByTableIdAndReservationId(1L, 1L);
        Mockito.verify(tableRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(tableReservationRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyFindByTableIdAndReservationIdThrowException() {
        String message = "Reservation ID doesn't match with the User ID.";

        Mockito.when(tableRepository.findById(1L))
                .thenReturn(Optional.of(Table.builder().build()));

        Mockito.when(tableReservationRepository.findById(1L))
                .thenReturn(Optional.of(TableReservation.builder()
                        .id(1L)
                        .table(Table.builder()
                                .id(1L)
                                .build())
                        .build()));

        RecordBadRequestException exception = Assertions.assertThrows(RecordBadRequestException.class,
                () -> tableReservationService.findByTableIdAndReservationId(1L, 1L));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyFindAllByUserId() {
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(User.builder().build()));

        tableReservationService.findAllByUserId(1L);
        Mockito.verify(tableReservationRepository, Mockito.times(1))
                .findAllByUser(User.builder().build());
    }

    @Test
    public void verifyFindByUserIdAndTableReservationId() {
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(User.builder()
                        .id(1L)
                        .build()));

        Mockito.when(tableReservationRepository.findById(1L))
                .thenReturn(Optional.of(TableReservation.builder()
                        .id(1L)
                        .user(User.builder().id(1L)
                                .build())
                        .build()));

        tableReservationService.findByUserIdAndTableReservationId(1L, 1L);
        Mockito.verify(tableReservationRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void verifyFindByUserIdAndTableReservationIdThrowException() {
        String message = "Reservation ID doesn't match with the User ID.";

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(User.builder()
                        .id(1L)
                        .build()));

        Mockito.when(tableReservationRepository.findById(1L))
                .thenReturn(Optional.of(TableReservation.builder()
                        .id(1L)
                        .user(User.builder()
                                .id(2L)
                                .build())
                        .build()));

        RecordBadRequestException exception = Assertions.assertThrows(RecordBadRequestException.class,
                () -> tableReservationService.findByUserIdAndTableReservationId(1L, 1L));

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    public void verifyFindAll() {
        Mockito.when(tableReservationRepository.findAll())
                        .thenReturn(List.of(TableReservation.builder().build()));

        tableReservationService.findAll();
        Mockito.verify(tableReservationRepository, Mockito.times(1)).findAll();
    }

}
