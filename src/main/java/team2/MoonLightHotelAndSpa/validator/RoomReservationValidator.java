package team2.MoonLightHotelAndSpa.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReservationValidator {

    private final RoomReservationRepository roomReservationRepository;
    private final RoomReservationService roomReservationService;

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

    public void roomReservationIdMatch(Long roomId, Long roomReservationId) {
        RoomReservation roomReservation = roomReservationService.findById(roomReservationId);
        if (!roomReservation.getRoom().getId().equals(roomId)) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the room ID.");
        }
    }
}
