package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;
import team2.MoonLightHotelAndSpa.repositories.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.services.RoomReservationService;

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
}
