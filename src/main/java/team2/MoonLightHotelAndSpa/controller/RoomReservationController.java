package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "room/reservations")
@Tag(name = "Room Reservations")
public class RoomReservationController {

    private RoomReservationService roomReservationService;

    @GetMapping
    public List<RoomReservation> findAll() {
        return roomReservationService.findAll();
    }
}
