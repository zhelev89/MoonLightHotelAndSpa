package team2.MoonLightHotelAndSpa.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.repository.RoomRepository;

@Component
@AllArgsConstructor
public class RoomValidator {

    private final RoomRepository roomRepository;

    public void existById(long id) {
        if (!roomRepository.existsById(id)) {
            throw new RecordBadRequestException(String.format("Room with id:%s, not exists.", id));
        }
    }

    public void existsByTitle(RoomTitle roomTitle) {
        if (!roomRepository.existsByTitle(roomTitle)) {
            throw new RecordBadRequestException(String.format("Room with Title:%s, not exists", roomTitle));
        }
    }
}
