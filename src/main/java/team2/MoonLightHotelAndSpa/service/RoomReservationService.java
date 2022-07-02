package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;

import java.util.List;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    List<RoomReservation> findAll();
}
