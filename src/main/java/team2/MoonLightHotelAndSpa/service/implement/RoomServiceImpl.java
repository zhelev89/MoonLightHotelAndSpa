package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
<<<<<<< HEAD:src/main/java/team2/MoonLightHotelAndSpa/service/implement/RoomServiceImpl.java
=======
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
>>>>>>> 89d583a1201d591310ebae26e7a60c33c66837ca:src/main/java/team2/MoonLightHotelAndSpa/services/Implements/RoomServiceImpl.java
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.repository.RoomRepository;
import team2.MoonLightHotelAndSpa.service.RoomService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
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

    @Override
    public Room update(Long id, Room updatedRoom) {
        Objects.requireNonNull(id);

        Room room = findById(id);
        room.setTitle(updatedRoom.getTitle());
        room.setImage(updatedRoom.getImage());
        room.setImages(updatedRoom.getImages());
        room.setDescription(updatedRoom.getDescription());
        room.setFacilities(updatedRoom.getFacilities());
        room.setArea(updatedRoom.getArea());
        room.setView(updatedRoom.getView());
        room.setPeople(updatedRoom.getPeople());
        room.setPrice(updatedRoom.getPrice());
        return room;
    }

    @Override
    public Room findById(Long id) {
        Objects.requireNonNull(id);
        return roomRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Room with id:%s, not found", id)));
    }

    @Override
    public void deleteById(Long id) {
        try {
            roomRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("Room with id:%s, not found.", id));
        }
    }
}
