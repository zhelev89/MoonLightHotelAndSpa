package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.models.rooms.Room;
import team2.MoonLightHotelAndSpa.repositories.RoomRepository;
import team2.MoonLightHotelAndSpa.services.RoomService;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
            Objects.requireNonNull(room);
            return roomRepository.save(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
