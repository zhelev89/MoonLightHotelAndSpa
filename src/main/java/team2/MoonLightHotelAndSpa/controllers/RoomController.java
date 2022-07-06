package team2.MoonLightHotelAndSpa.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.convertors.RoomConvertor;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserResponse;
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
    @ApiOperation(value = "Save room", notes = "Use this endpoint to save a room in DB.", response = RoomResponse.class)
    private ResponseEntity<RoomResponse> save(@RequestBody RoomSaveRequest roomSaveRequest) {
        Room room = roomConvertor.convert(roomSaveRequest);
        Room savedRoom = roomService.save(room);
        RoomResponse response = roomConvertor.convert(savedRoom);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @ApiOperation(value = "Find all", notes = "Use this endpoint to find all rooms", response = RoomResponse.class)
    private ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok().body(roomService.findAll());
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update room", notes = "Use this endpoint to update update room", response = RoomResponse.class)
    public ResponseEntity<RoomResponse> update(@RequestBody RoomUpdateRequest roomUpdateRequest,@PathVariable Long id) {
        Room convertedRoom = roomConvertor.convert(roomUpdateRequest);
        Room updatedRoom = roomService.update(id, convertedRoom);
        RoomResponse roomResponse = roomConvertor.convert(updatedRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find by ID", notes = "Use this endpoint to find a room by ID", response = RoomResponse.class)
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        Room foundRoom = roomService.findById(id);
        RoomResponse roomResponse = roomConvertor.convert(foundRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Find by ID", notes = "Use this endpoint to delete a room by ID", response = ResponseEntity.class)
    public ResponseEntity<HttpStatus> deleteById (@PathVariable Long id){
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
