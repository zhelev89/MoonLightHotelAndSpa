package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.convertors.RoomConvertor;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.models.rooms.Room;
import team2.MoonLightHotelAndSpa.services.RoomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    private RoomConvertor roomConvertor;

    @PostMapping
    private ResponseEntity<Room> save(@RequestBody RoomSaveRequest roomSaveRequest) {
        Room room = roomConvertor.convert(roomSaveRequest);
        Room savedRoom = roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedRoom);
    }

    @GetMapping
    private List<Room> findAll() {
        return roomService.findAll();
    }
}
