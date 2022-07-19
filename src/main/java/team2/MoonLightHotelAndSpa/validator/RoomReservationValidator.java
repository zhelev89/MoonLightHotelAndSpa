package team2.MoonLightHotelAndSpa.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReservationValidator {

    private final RoomReservationRepository roomReservationRepository;

    public void existsById(Long id) {
        if (!roomReservationRepository.existsById(id)) {
            throw new RecordBadRequestException(String.format("Room reservation with id:%s, not exists.", id));
        }
    }

    public void validDates(Instant startDate, Instant endDate) {
        if (endDate.isBefore(startDate) || startDate.isBefore(Instant.now())) {
            throw new RecordBadRequestException("Incorrect dates");
        }
    }

    public void validGuestNumber(Integer roomPeople, Integer roomReservePeople) {
        if (roomPeople < roomReservePeople) {
            throw new RecordBadRequestException(String.format("This room is for %s people!", roomPeople));
        }
    }
}
