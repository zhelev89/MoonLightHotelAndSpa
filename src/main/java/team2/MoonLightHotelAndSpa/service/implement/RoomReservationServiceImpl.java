package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {

    private final UserService userService;
    private final RoomReservationRepository roomReservationRepository;

    public RoomReservation save(RoomReservation roomReservation) {
        Objects.requireNonNull(roomReservation);
        return roomReservationRepository.save(roomReservation);
    }

    public RoomReservation findByUserIdAndReservationId(Long uid, Long rid) {
        Objects.requireNonNull(uid);
        Objects.requireNonNull(rid);

        User foundUser = userService.findById(uid);
        RoomReservation foundReservation = findById(rid);
        if (!foundReservation.getUser().getId().equals(foundUser.getId())) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }
        return foundReservation;
    }

    public Set<RoomReservation> findAllByUserId(Long id) {
        Objects.requireNonNull(id);
        User userById = userService.findById(id);
        return roomReservationRepository.findAllByUser(userById);
    }

    public Set<RoomReservation> findAll() {

        return new HashSet<>(roomReservationRepository.findAll());
    }

    @Override
    public int calculateDays(Instant startDate, Instant endDate) {

        long daysLong = Duration.between(startDate, endDate).toDays();
        if (daysLong <= 0) {
            throw new RecordBadRequestException("Days should be more than 0");
        }
        return (int) daysLong;
    }

    @Override
    public RoomReservation findById(Long id) {
        return roomReservationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Room reservation with id:%s, not found", id)));
    }

    @Override
    public void deleteById(Long id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    public void roomReservationIdMatch(Long roomId, Long roomReservationId) {
        RoomReservation roomReservation = findById(roomReservationId);
        if (!roomReservation.getRoom().getId().equals(roomId)) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the room ID.");
        }
    }
}
