package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.convertor.RoomConvertor;
import team2.MoonLightHotelAndSpa.dataTransferObject.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    private RoomConvertor roomConvertor;

    @PostMapping
    private ResponseEntity<RoomResponse> save(@RequestBody RoomSaveRequest roomSaveRequest) {
        Room room = roomConvertor.convert(roomSaveRequest);
        Room savedRoom = roomService.save(room);
        RoomResponse response = roomConvertor.convert(savedRoom);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    private ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok().body(roomService.findAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoomResponse> update(@RequestBody RoomUpdateRequest roomUpdateRequest,@PathVariable Long id) {
        Room convertedRoom = roomConvertor.convert(roomUpdateRequest);
        Room updatedRoom = roomService.update(id, convertedRoom);
        RoomResponse roomResponse = roomConvertor.convert(updatedRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        Room foundRoom = roomService.findById(id);
        RoomResponse roomResponse = roomConvertor.convert(foundRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById (@PathVariable Long id){
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
