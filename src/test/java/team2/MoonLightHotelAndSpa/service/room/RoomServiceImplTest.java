package team2.MoonLightHotelAndSpa.service.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomImage;
import team2.MoonLightHotelAndSpa.model.room.RoomView;
import team2.MoonLightHotelAndSpa.repository.RoomRepository;
import team2.MoonLightHotelAndSpa.service.room.RoomServiceImpl;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static team2.MoonLightHotelAndSpa.model.room.RoomTitle.APARTMENT;
import static team2.MoonLightHotelAndSpa.model.room.RoomTitle.STANDARD;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    private RoomServiceImpl roomService;

    @BeforeEach
    public void setUp() {
        roomService = new RoomServiceImpl(roomRepository);
    }

    @Test
    public void verifySave() {
        Room room = Room.builder().build();
        roomService.save(room);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    public void verifyFindAll() {
        when(roomRepository.findAll()).thenReturn(List.of(Room.builder().build()));
        roomService.findAll();
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void verifyUpdate() {
        Room room = Room.builder()
                .id(1L)
                .title(STANDARD)
                .image("image")
                .images(Set.of(RoomImage.builder().build()))
                .description("This room...")
                .facilities("Facilites")
                .area(1234)
                .view(RoomView.SEA)
                .people(3)
                .price(120F)
                .count(2)
                .build();
        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(Room.builder()
                .id(1L)
                .title(APARTMENT)
                .image("image1")
                .images(Set.of(RoomImage.builder().build()))
                .description("This room is ...")
                .facilities("Facilites...")
                .area(14)
                .view(RoomView.GARDEN)
                .people(4)
                .price(200F)
                .count(4)
                .build()));
        Room updatedRoom = roomService.update(room.getId(), room);
        assertEquals(room.getTitle(), updatedRoom.getTitle());
        assertEquals(room.getImage(), updatedRoom.getImage());
        assertEquals(room.getImages(), updatedRoom.getImages());
        assertEquals(room.getDescription(), updatedRoom.getDescription());
        assertEquals(room.getFacilities(), updatedRoom.getFacilities());
        assertEquals(room.getArea(), updatedRoom.getArea());
        assertEquals(room.getView(), updatedRoom.getView());
        assertEquals(room.getPeople(), updatedRoom.getPeople());
        assertEquals(room.getPrice(), updatedRoom.getPrice());
        assertEquals(room.getCount(), updatedRoom.getCount());
    }

    @Test
    public void verifyFindById() {
        Long id = 1L;
        when(roomRepository.findById(id)).thenReturn(Optional.of(Room.builder().build()));
        roomService.findById(id);
        verify(roomRepository, times(1)).findById(id);
    }

    @Test
    public void verifyFindByIdThrowsException() {
        String exceptionMessage = "Room with id:1, not found";
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            roomService.findById(1L);
        });
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void verifyDeleteById() {
        Room room = Room.builder()
                .id(1L)
                .build();
        roomService.deleteById(room.getId());
        verify(roomRepository, times(1)).deleteById(room.getId());
    }
}
