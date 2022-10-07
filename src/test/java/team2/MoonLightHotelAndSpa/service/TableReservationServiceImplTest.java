package team2.MoonLightHotelAndSpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.repository.TableRepository;
import team2.MoonLightHotelAndSpa.repository.TableReservationRepository;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.service.implement.TableReservationServiceImpl;
import team2.MoonLightHotelAndSpa.service.implement.TableServiceImpl;
import team2.MoonLightHotelAndSpa.service.implement.UserServiceImpl;

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

//    @Test
//    public void verifyFindByTableIdAndReservationId() {
//        long tableId = 1;
//        long reservationId = 1;
//
////        Mockito.when(tableService.findById(tableId))
////                .thenReturn(Optional.of(Table.builder().id(tableId).build()));
//        Mockito.when(tableReservationService.findById(reservationId))
//                        .thenReturn(TableReservation.builder().id(reservationId).build());
//        Mockito.when(tableReservationService.findByTableIdAndReservationId(tableId, reservationId))
//                .thenReturn(TableReservation.builder().build());
//        Mockito.verify(tableRepository, Mockito.times(1)).findById(tableId);
//        Mockito.verify(tableReservationRepository, Mockito.times(1)).findById(reservationId);
//    }
}
