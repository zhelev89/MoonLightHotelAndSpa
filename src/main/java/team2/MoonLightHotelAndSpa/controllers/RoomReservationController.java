package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;
import team2.MoonLightHotelAndSpa.services.RoomReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "room/reservations")
public class RoomReservationController {

    private RoomReservationService roomReservationService;

    @GetMapping
    public List<RoomReservation> findAll() {
        return roomReservationService.findAll();
    }
}
