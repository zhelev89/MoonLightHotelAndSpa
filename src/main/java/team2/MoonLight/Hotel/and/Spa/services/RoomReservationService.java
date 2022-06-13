package team2.MoonLight.Hotel.and.Spa.services;

import team2.MoonLight.Hotel.and.Spa.models.reservations.RoomReservation;

import java.util.List;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    List<RoomReservation> findAll();
}
