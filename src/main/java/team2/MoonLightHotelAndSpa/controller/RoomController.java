package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.RoomConverter;
import team2.MoonLightHotelAndSpa.converter.RoomReserveConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReserve.RoomReserveSaveRequest;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReserveService;
import team2.MoonLightHotelAndSpa.service.RoomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rooms")
@Tag(name = "Room")
public class RoomController {

    private final RoomService roomService;
    private final RoomConverter roomConverter;
    private final RoomReserveService roomReserveService;
    private final RoomReserveConverter roomReserveConverter;

    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @PostMapping
    @Operation(summary = "Save room")
    private ResponseEntity<RoomResponse> save(@RequestBody RoomSaveRequest roomSaveRequest) {
        Room room = roomConverter.convert(roomSaveRequest);
        Room savedRoom = roomService.save(room);
        RoomResponse response = roomConverter.convert(savedRoom);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @Operation(summary = "Get all rooms")
    private ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok().body(roomService.findAll());
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update room")
    public ResponseEntity<RoomResponse> update(@RequestBody RoomUpdateRequest roomUpdateRequest, @PathVariable Long id) {
        Room convertedRoom = roomConverter.convert(roomUpdateRequest);
        Room updatedRoom = roomService.update(id, convertedRoom);
        RoomResponse roomResponse = roomConverter.convert(updatedRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find room by ID")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        Room foundRoom = roomService.findById(id);
        RoomResponse roomResponse = roomConverter.convert(foundRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete room by ID")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @PostMapping(value = "/{id}/reservation")
    private ResponseEntity<RoomReserveResponse> save(@RequestBody RoomReserveSaveRequest roomReserveSaveRequest, @PathVariable Long id) {
        RoomReserve convert = roomReserveConverter.convert(roomReserveSaveRequest, id);
        RoomReserve savedReserve = roomReserveService.save(convert);
        RoomReserveResponse response = roomReserveConverter.convert(savedReserve);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
