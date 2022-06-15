package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;

import java.util.List;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    List<RoomReservation> findAll();
}
