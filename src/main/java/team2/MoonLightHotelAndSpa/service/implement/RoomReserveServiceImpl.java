package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.repository.RoomReservationRepository;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomReserveServiceImpl implements RoomReserveService {

    private final RoomReservationRepository roomReservationRepository;

    public RoomReserve save(RoomReserve roomReserve) {
        Objects.requireNonNull(roomReserve);
        return roomReservationRepository.save(roomReserve);
    }

    public List<RoomReserve> findAll() {
        return roomReservationRepository.findAll();
    }

    @Override
    public Integer calculateDays(Instant startDate, Instant endDate) {
        Long daysLong = Duration.between(startDate,endDate).toDays();
        if(daysLong <= 0) {
            throw new RecordBadRequestException("Days should be more than 0");
        }
        return daysLong.intValue();
    }
}