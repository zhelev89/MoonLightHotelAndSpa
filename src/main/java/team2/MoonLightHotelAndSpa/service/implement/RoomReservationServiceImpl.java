package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.RoomReserveRepository;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.validator.RoomReserveValidator;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReserveService {

    private final UserService userService;
    private final RoomReserveRepository roomReserveRepository;
    private final RoomReserveValidator roomReserveValidator;

    public RoomReserve save(RoomReserve roomReserve) {
        Objects.requireNonNull(roomReserve);
        return roomReserveRepository.save(roomReserve);
    }

    public Set<RoomReserve> findAllByUserId(Long id) {
        Objects.requireNonNull(id);
        User userById = userService.findById(id);
        return roomReserveRepository.findAllByUser(userById);
    }

    public Set<RoomReserve> findAll() {
        return new HashSet<>(roomReserveRepository.findAll());
    }

    @Override
    public Integer calculateDays(Instant startDate, Instant endDate) {
        roomReserveValidator.validDates(startDate, endDate);
        Long daysLong = Duration.between(startDate, endDate).toDays();
        if (daysLong <= 0) {
            throw new RecordBadRequestException("Days should be more than 0");
        }
        return daysLong.intValue();
    }
}
