package team2.MoonLightHotelAndSpa.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.converter.room.RoomReservationConverter;
import team2.MoonLightHotelAndSpa.converter.table.TableReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponseV2;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationResponse;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.service.room.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.table.TableReservationService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
@Tag(name = "User")
public class UserReservationController {

    private final RoomReservationService roomReservationService;
    private final RoomReservationConverter roomReservationConverter;
    private final TableReservationService tableReservationService;
    private final TableReservationConverter tableReservationConverter;

    @GetMapping(value = "/reservations")
    @Operation(summary = "Show user reservations", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<Set<RoomReservationResponseV2>> userReservations() {
        return ResponseEntity.ok().body(roomReservationConverter.convert(roomReservationService.findAll()));
    }

    @GetMapping(value = "/{uid}/reservations")
    @Operation(summary = "Show reservations by user ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })

    public ResponseEntity<Set<RoomReservationResponseV2>> showReservationsByUserId(@PathVariable long uid) {
        Set<RoomReservationResponseV2> roomReservationResponseV2Set =
                roomReservationConverter.convert(roomReservationService.findAllByUserId(uid));
        return ResponseEntity.ok().body(roomReservationResponseV2Set);
    }

    @GetMapping(value = "/{uid}/reservations/{rid}")
    @Operation(summary = "Show reservations by ID and user ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomReservationResponseV2> getReservationByUserIdAndReservationId(
            @PathVariable long uid, @PathVariable long rid) {
        RoomReservation byUserIdAndReservationId = roomReservationService.findByUserIdAndReservationId(uid, rid);
        RoomReservationResponseV2 roomReservationResponseV2 = roomReservationConverter.convertForUpdate(byUserIdAndReservationId);
        return ResponseEntity.ok().body(roomReservationResponseV2);
    }

    @GetMapping(value = "/tables/reservations")
    public ResponseEntity<Set<TableReservationResponse>> getTableReservationByUserId() {
        Set<TableReservation> all = tableReservationService.findAll();
        return ResponseEntity.ok().body(all.stream()
                .map(tableReservationConverter::convert)
                .collect(Collectors.toSet()));
    }


    @GetMapping(value = "/{uid}/tables/reservations")
    public ResponseEntity<Set<TableReservationResponse>> getTableReservationByUserId(@PathVariable long uid) {
        Set<TableReservation> allByUserId = tableReservationService.findAllByUserId(uid);
        return ResponseEntity.ok().body(allByUserId.stream()
                .map(tableReservationConverter::convert)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/{uid}/tables/reservation/{rid}")
    public ResponseEntity<TableReservationResponse> getTableReservationByUserIdAndReservationId(
            @PathVariable long uid, @PathVariable long rid) {

        TableReservation tableReservation = tableReservationService.findByUserIdAndTableReservationId(uid, rid);
        TableReservationResponse tableReservationResponse = tableReservationConverter.convert(tableReservation);
        return ResponseEntity.ok().body(tableReservationResponse);
    }
}
