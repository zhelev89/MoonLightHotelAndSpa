package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.convertor.RoomReservationConvertor;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationSaveRequest;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/room/reservations")
@Tag(name = "Room Reservations")
public class RoomReservationController {

    private RoomReservationService roomReservationService;

    @GetMapping
    public List<RoomReservation> findAll() {
        return roomReservationService.findAll();
    }

    @PostMapping
    private ResponseEntity<RoomReservation> save(@RequestBody RoomReservationSaveRequest roomReservationSaveRequest) {
        RoomReservation roomReservation = RoomReservationConvertor.convert(roomReservationSaveRequest);
        RoomReservation savedRoomReservation = roomReservationService.save(roomReservation);
        //RoomReservationResponse response = RoomReservationConvertor.convert(savedRoomReservation);
        return ResponseEntity.ok().body(savedRoomReservation);
    }
}
