package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.repository.RoomReserveRepository;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;
import team2.MoonLightHotelAndSpa.service.RoomReserveValidator;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomReserveServiceImpl implements RoomReserveService, RoomReserveValidator {

    private final RoomReserveRepository roomReserveRepository;

    public RoomReserve save(RoomReserve roomReserve) {
        Objects.requireNonNull(roomReserve);
        return roomReserveRepository.save(roomReserve);
    }

    public List<RoomReserve> findAll() {
        return roomReserveRepository.findAll();
    }

    @Override
    public Integer calculateDays(Instant startDate, Instant endDate) {
        Long daysLong = Duration.between(startDate, endDate).toDays();
        if (daysLong <= 0) {
            throw new RecordBadRequestException("Days should be more than 0");
        }
        return daysLong.intValue();
    }

    public boolean existsById(Long id) {
        return roomReserveRepository.existsById(id);
    }

    public boolean isValidDates(Instant startDate, Instant endDate) {
        return endDate.isAfter(startDate) && startDate.isAfter(Instant.now());
    }

    public boolean isValidGuestNumber(Integer roomPeople, Integer roomReservePeople) {
        return roomPeople >= roomReservePeople;
    }
}
