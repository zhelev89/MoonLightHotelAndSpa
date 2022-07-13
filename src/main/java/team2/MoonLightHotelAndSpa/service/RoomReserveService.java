package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;

import java.time.Instant;
import java.util.List;

public interface RoomReserveService {

    RoomReserve save(RoomReserve roomReserve);

    List<RoomReserve> findAll();

    Integer calculateDays(Instant startDate, Instant endDate);
}
