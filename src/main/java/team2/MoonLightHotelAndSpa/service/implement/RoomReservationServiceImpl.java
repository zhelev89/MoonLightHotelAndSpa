package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    public RoomReservation save(RoomReservation roomReservation) {
        Objects.requireNonNull(roomReservation);
        return roomReservationRepository.save(roomReservation);
    }

    public List<RoomReservation> findAll() {
        return roomReservationRepository.findAll();
    }

    @Override
    public Integer calculateDays(Instant startDate, Instant endDate) {
        Long daysLong = Duration.between(startDate,endDate).toDays();
        return daysLong.intValue();
    }
}
