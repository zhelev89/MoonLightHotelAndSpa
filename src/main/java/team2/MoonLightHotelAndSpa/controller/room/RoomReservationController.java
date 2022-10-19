package team2.MoonLightHotelAndSpa.controller.room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.room.RoomConverter;
import team2.MoonLightHotelAndSpa.converter.room.RoomReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponseV1;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponseV2;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;
import team2.MoonLightHotelAndSpa.service.room.RoomReservationService;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rooms")
@Tag(name = "Room Reservation")
public class RoomReservationController {

    private final RoomReservationService roomReservationService;
    private final RoomReservationConverter roomReservationConverter;
    private final RoomConverter roomConverter;

    @PostMapping(value = "/{id}/reservation")
    @Operation(summary = "Create room reservation", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV1.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<RoomReservationResponseV1> save(@RequestBody RoomReservationSaveRequest roomReservationSaveRequest,
                                                           @PathVariable long id) {
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
    public ResponseEntity<HttpStatus> deleteReservationById(@PathVariable long id, @PathVariable long rid) {
        roomReservationService.roomReservationIdMatch(id, rid);
        roomReservationService.deleteById(rid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}/reservation/{rid}")
    @Operation(summary = "Update a Reservation by ID and room ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomReservationResponseV2> update(@RequestBody @Valid RoomReservationUpdateRequest roomReservationUpdateRequest,
                                                            @PathVariable long id, @PathVariable long rid) {
        roomReservationService.roomReservationIdMatch(id, rid);
        RoomReservation convertedRoomReservation = roomReservationConverter.convert(roomReservationUpdateRequest);
        RoomReservation updatedRoomReservation = roomReservationService.update(id, rid, convertedRoomReservation);
        RoomReservationResponseV2 roomReservationResponseV2 = roomReservationConverter.convertForUpdate(updatedRoomReservation);
        return ResponseEntity.ok().body(roomReservationResponseV2);
    }

    @GetMapping(value = "/{id}/reservation/{rid}")
    @Operation(summary = "Show a Reservation by ID and room ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomReservationResponseV2> findReservationById(@PathVariable long id, @PathVariable long rid) {
        roomReservationService.roomReservationIdMatch(id, rid);
        RoomReservation foundRoomReservation = roomReservationService.findById(rid);
        RoomReservationResponseV2 responseV2 = roomReservationConverter.convertForFindAll(foundRoomReservation);
        return ResponseEntity.ok().body(responseV2);
    }

    @GetMapping(value = "/{id}/summarize")
    @Operation(summary = "Find all available rooms for a certain period and people", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<RoomResponse>> findAllAvailableRooms(@RequestParam String start_date,
                                                                    @RequestParam String end_date,
                                                                    @RequestParam int adults,
                                                                    @RequestParam int kids) {
        Instant startDate = Instant.parse(start_date);
        Instant endDate = Instant.parse(end_date);
        int people = adults + kids;
        List<Room> allAvailableRooms = roomReservationService.findAllAvailableRooms(startDate, endDate, people);
        List<RoomResponse> allAvailableRoomsResponse = allAvailableRooms.stream()
                .map(roomConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(allAvailableRoomsResponse);
    }

    @GetMapping(value = "/detailed")
    @Operation(summary = "Find all available rooms with detailed information", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<?> findAllAvailableRoomsDetailed(@RequestParam String start_date, @RequestParam String end_date,
                                                           @RequestParam int adults, @RequestParam int kids,
                                                           @RequestParam RoomView roomView, @RequestParam RoomTitle roomTitle) {
        return roomReservationService.findAllAvailableRoomsDetailed(start_date, end_date, adults, kids, roomView, roomTitle);
    }
}
