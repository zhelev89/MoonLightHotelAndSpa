package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;

import java.time.Instant;
import java.util.Set;

public interface RoomReserveService {

    RoomReserve save(RoomReserve roomReserve);

    Set<RoomReserve> findAllByUserId(Long id);

    Set<RoomReserve> findAll();

    Integer calculateDays(Instant startDate, Instant endDate);
}
