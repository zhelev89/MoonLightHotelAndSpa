package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.RoomConverter;
import team2.MoonLightHotelAndSpa.converter.RoomReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.*;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.RoomService;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rooms")
@Tag(name = "Room")
public class RoomController {

    private final RoomService roomService;
    private final RoomConverter roomConverter;
    private final RoomReservationService roomReservationService;
    private final RoomReservationConverter roomReservationConverter;

    @PostMapping
    @Operation(summary = "Save room", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<RoomResponse> save(@RequestBody RoomSaveRequest roomSaveRequest) {
        Room room = roomConverter.convert(roomSaveRequest);
        Room savedRoom = roomService.save(room);
        RoomResponse response = roomConverter.convert(savedRoom);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/getAllRooms")
    @Operation(summary = "Get all rooms", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))

    })
    private ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok().body(roomService.findAll());
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update room", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomResponse> update(@RequestBody RoomUpdateRequest roomUpdateRequest, @PathVariable Long id) {
        Room convertedRoom = roomConverter.convert(roomUpdateRequest);
        Room updatedRoom = roomService.update(id, convertedRoom);
        RoomResponse roomResponse = roomConverter.convert(updatedRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find room by ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        Room foundRoom = roomService.findById(id);
        RoomResponse roomResponse = roomConverter.convert(foundRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete room by ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{id}/reservation")
    @Operation(summary = "Create room reservation", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV1.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<RoomReservationResponseV1> save(@RequestBody RoomReservationSaveRequest roomReservationSaveRequest, @PathVariable Long id) {
        RoomReservation convert = roomReservationConverter.convert(roomReservationSaveRequest, id);
        RoomReservation savedReserve = roomReservationService.save(convert);
        RoomReservationResponseV1 response = roomReservationConverter.convert(savedReserve);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "/{id}/reservation/{rid}")
    @Operation(summary = "Delete reservation", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteReservationById(@PathVariable Long id, @PathVariable Long rid) {
        roomReservationService.roomReservationIdMatch(id, rid);
        roomReservationService.deleteById(rid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}/reservation/{rid}")
    @Operation(summary = "Update a Reservation by ID and room ID")
    public ResponseEntity<RoomReservationResponseV2> update(@RequestBody @Valid RoomReservationUpdateRequest roomReservationUpdateRequest, @PathVariable Long id, @PathVariable Long rid) {
        roomReservationService.roomReservationIdMatch(id, rid);
        RoomReservation convertedRoomReservation = roomReservationConverter.convert(roomReservationUpdateRequest);
        RoomReservation updatedRoomReservation = roomReservationService.update(id, rid, convertedRoomReservation);
        RoomReservationResponseV2 roomReservationResponseV2 = roomReservationConverter.convertForUpdate(updatedRoomReservation);
        return ResponseEntity.ok().body(roomReservationResponseV2);
    }

    @GetMapping(value = "/{id}/reservation/{rid}")
    public ResponseEntity<RoomReservationResponseV2> findReservationById(@PathVariable Long id, @PathVariable Long rid) {
        roomReservationService.roomReservationIdMatch(id, rid);
        RoomReservation foundRoomReservation = roomReservationService.findById(rid);
        RoomReservationResponseV2 responseV2 = roomReservationConverter.convertForFindAll(foundRoomReservation);
        return ResponseEntity.ok().body(responseV2);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> findAllAvailableRooms(@RequestBody RoomReservationQuery roomReservationQuery) {
        Instant startDate = Instant.parse(roomReservationQuery.getStart_date());
        Instant endDate = Instant.parse(roomReservationQuery.getEnd_date());
        int people = roomReservationQuery.getAdults() + roomReservationQuery.getKids();
        List<Room> allAvailableRooms = roomReservationService.findAllAvailableRooms(startDate, endDate, people);
        List<RoomResponse> allAvailableRoomsResponse = allAvailableRooms.stream().map(roomConverter::convert).collect(Collectors.toList());
        return ResponseEntity.ok().body(allAvailableRoomsResponse);
    }
}