package team2.MoonLightHotelAndSpa.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.car.CarTransferConverter;
import team2.MoonLightHotelAndSpa.converter.room.RoomReservationConverter;
import team2.MoonLightHotelAndSpa.converter.screen.ScreenReservationConverter;
import team2.MoonLightHotelAndSpa.converter.table.TableReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.roomReservation.RoomReservationResponseV2;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationResponse;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.car.CarTransferService;
import team2.MoonLightHotelAndSpa.service.room.RoomReservationService;
import team2.MoonLightHotelAndSpa.service.screen.ScreenReservationService;
import team2.MoonLightHotelAndSpa.service.table.TableReservationService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
@Tag(name = "User Reservation")
@CrossOrigin
public class UserReservationController {

    private final UserService userService;
    private final RoomReservationService roomReservationService;
    private final RoomReservationConverter roomReservationConverter;
    private final TableReservationService tableReservationService;
    private final TableReservationConverter tableReservationConverter;
    private final ScreenReservationService screenReservationService;
    private final ScreenReservationConverter screenReservationConverter;
    private final CarTransferService carTransferService;
    private CarTransferConverter carTransferConverter;

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
    @Operation(summary = "Show user tables reservations", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<Set<TableReservationResponse>> getTableReservationByUserId() {
        Set<TableReservation> all = tableReservationService.findAll();
        return ResponseEntity.ok().body(all.stream()
                .map(tableReservationConverter::convert)
                .collect(Collectors.toSet()));
    }


    @GetMapping(value = "/{uid}/tables/reservations")
    @Operation(summary = "Show tables reservations by user ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<Set<TableReservationResponse>> getTableReservationByUserId(@PathVariable long uid) {
        Set<TableReservation> allByUserId = tableReservationService.findAllByUserId(uid);
        return ResponseEntity.ok().body(allByUserId.stream()
                .map(tableReservationConverter::convert)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/{uid}/tables/reservation/{rid}")
    @Operation(summary = "Show table reservation by ID and user ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<TableReservationResponse> getTableReservationByUserIdAndReservationId(
            @PathVariable long uid, @PathVariable long rid) {

        TableReservation tableReservation = tableReservationService.findByUserIdAndTableReservationId(uid, rid);
        TableReservationResponse tableReservationResponse = tableReservationConverter.convert(tableReservation);
        return ResponseEntity.ok().body(tableReservationResponse);
    }

    @GetMapping(value = "/screens/reservations")
    @Operation(summary = "Show users screen reservations", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<ScreenReservationResponse>> showUserScreenReservations(@AuthenticationPrincipal User user) {
        List<ScreenReservation> screenReservations = screenReservationService.findByUser(user);
        return ResponseEntity.ok().body(screenReservations.stream()
                .map(screenReservationConverter::convert)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{uid}/screens/reservations")
    @Operation(summary = "Show screen reservations by user ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<ScreenReservationResponse>> showUserScreenReservationsById(@PathVariable long uid) {
        User user = userService.findById(uid);
        List<ScreenReservation> screenReservations = screenReservationService.findByUser(user);
        return ResponseEntity.ok().body(screenReservations.stream()
                .map(screenReservationConverter::convert)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{uid}/screens/reservation/{rid}")
    @Operation(summary = "Show screen reservation by ID and user ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<ScreenReservationResponse> showUserByIdAndScreenReservationsById(@PathVariable long uid,
                                                                                           @PathVariable long rid) {
        ScreenReservation screenReservation = screenReservationService.findByUserIdAndReservationId(uid, rid);
        ScreenReservationResponse response = screenReservationConverter.convert(screenReservation);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/transfers")
    public ResponseEntity<List<CarTransferResponse>> findAllTransfers() {
        return ResponseEntity.ok().body(carTransferService.findAll().stream().map(carTransfer -> carTransferConverter.convertFindAll(carTransfer)).toList());
    }

    @GetMapping(value = "/{uid}/transfers")
    public ResponseEntity<List<CarTransferResponse>> findTransfersByUserId(@PathVariable long uid) {
        return ResponseEntity.ok().body(carTransferService.findAllByUserId(uid).stream().map(carTransfer -> carTransferConverter.convertFindAll(carTransfer)).toList());
    }

    @GetMapping(value = "/{uid}/transfers/{tid}")
    public ResponseEntity<CarTransferResponse> findTransferByUserIdAndTransferId(@PathVariable long uid, @PathVariable long tid) {
        return ResponseEntity.ok().body(carTransferConverter.convertFindAll(carTransferService.findByUserIdAndTransferId(uid, tid)));
    }
}
