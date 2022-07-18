package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;

import java.time.Instant;
import java.util.List;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    List<RoomReservation> findAll();

    Integer calculateDays(Instant startDate, Instant endDate);
}
