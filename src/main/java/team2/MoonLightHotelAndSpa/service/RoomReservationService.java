package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;

import java.time.Instant;
import java.util.Set;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    Set<RoomReservation> findAllByUserId(Long id);

    Set<RoomReservation> findAll();

    Integer calculateDays(Instant startDate, Instant endDate);
}
