package team2.MoonLightHotelAndSpa.controller.screen;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.screen.ScreenReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.*;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.screen.ScreenReservationService;
import team2.MoonLightHotelAndSpa.service.screen.ScreenService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/screens")
@Tag(name = "Screen Reservation")
public class ScreenReservationController {

    private final ScreenReservationService screenReservationService;
    private final ScreenReservationConverter screenReservationConverter;
    private final ScreenService screenService;
    private final UserService userService;

    @PostMapping(value = "/{id}/summarize")
    @Operation(summary = "Summarize screen reservation", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<Integer>> findFreeSeatsByScreenIdAndDate(@PathVariable long id,
                                                                        @RequestBody ScreenRequestFindFreeSeats screenRequestFindFreeSeats) {
        List<Integer> freeSeats =
                screenReservationService.findFreeSeatsByScreenIdAndDate(id, screenRequestFindFreeSeats.getDate());
        return ResponseEntity.ok().body(freeSeats);
    }

    @PostMapping(value = "/{id}/reservations")
    @Operation(summary = "Save screen reservation", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<ScreenReservationResponse> createReservation(@PathVariable long id,
                                                                        @RequestBody ScreenReservationRequest screenReservationRequest,
                                                                        @AuthenticationPrincipal User user) {

        ScreenReservation screenReservation = screenReservationConverter.convert(screenReservationRequest);
        screenReservation.setScreen(screenService.findById(id));
        screenReservation.setUser(userService.findById(user.getId()));
        ScreenReservation savedScreenReservation = screenReservationService.save(screenReservation);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(screenReservationConverter.convert(savedScreenReservation));
    }

    @GetMapping(value = "/{id}/reservations")
    @Operation(summary = "Show reservations by screen ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<List<ScreenReservationResponse>> findAllByScreenId(@PathVariable long id) {
        List<ScreenReservation> allByScreenId = screenReservationService.findAllByScreenId(id);
        return ResponseEntity.ok().body(allByScreenId.stream()
                .map(screenReservationConverter::convert)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}/reservations/{rid}")
    @Operation(summary = "Show reservation by ID and screen ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<ScreenReservationResponseV2> findByIdAndRid(@PathVariable long id,
                                                                       @PathVariable long rid) {
        ScreenReservation screenReservation = screenReservationService.findByScreenIdAndReservationId(id, rid);
        ScreenReservationResponseV2 responseV2 = screenReservationConverter.convert(screenReservation, screenReservation.getUser());
        return ResponseEntity.ok().body(responseV2);
    }

    @PutMapping(value = "/{id}/reservations/{rid}")
    @Operation(summary = "Update a reservation by ID and screen ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScreenReservationResponseV2.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<ScreenReservationResponseV2> updateByScreenIdAndReservationId(@PathVariable long id,
                                                                                         @PathVariable long rid,
                                                                                         @RequestBody ScreenReservationUpdateRequest screenReservationUpdateRequest) {
        ScreenReservation screenReservation = screenReservationService.updateByScreenIdAndReservationId(
                id, rid, screenReservationUpdateRequest);
        ScreenReservationResponseV2 responseV2 = screenReservationConverter.convert(
                screenReservation, screenReservation.getUser());
        return ResponseEntity.ok().body(responseV2);
    }

    @DeleteMapping(value = "/{id}/reservations/{rid}")
    @Operation(summary = "Delete a reservation by ID and screen ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<HttpStatus> deleteByScreenIdAndReservationId(@PathVariable long id, @PathVariable long rid) {
        screenReservationService.deleteByScreenIdAndReservationsId(id, rid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
