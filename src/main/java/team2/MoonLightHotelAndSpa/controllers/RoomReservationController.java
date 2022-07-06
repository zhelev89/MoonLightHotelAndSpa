package team2.MoonLightHotelAndSpa.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserResponse;
import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;
import team2.MoonLightHotelAndSpa.services.RoomReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "room/reservations")
@Api(value = "Room Reservation", description = "Room Reservation")
public class RoomReservationController {

    private RoomReservationService roomReservationService;

    @GetMapping
    @ApiOperation(value = "Find All", notes = "Use this endpoint to find all room reservations.", response = RoomReservation.class)
    public List<RoomReservation> findAll() {
        return roomReservationService.findAll();
    }
}
