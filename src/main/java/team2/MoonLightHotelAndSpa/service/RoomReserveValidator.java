package team2.MoonLightHotelAndSpa.service;

import java.time.Instant;

public interface RoomReserveValidator {
    boolean existsById(Long id);

    boolean isValidDates(Instant startDate, Instant endDate);

    boolean isValidGuestNumber(Integer roomPeople, Integer roomReservePeople);
}
