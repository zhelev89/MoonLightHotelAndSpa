package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;

import java.time.Instant;
import java.util.Set;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    RoomReservation findById(Long id);

    RoomReservation findByUserIdAndReservationId(Long uid, Long rid);

    Set<RoomReservation> findAllByUserId(Long id);

    Set<RoomReservation> findAll();

    Integer calculateDays(Instant startDate, Instant endDate);

    RoomReservation findById(Long id);

    void deleteById(Long id);

    void roomReservationIdMatch(Long roomId, Long roomReservationId);
}
