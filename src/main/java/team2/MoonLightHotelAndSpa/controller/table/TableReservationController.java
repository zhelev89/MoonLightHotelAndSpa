package team2.MoonLightHotelAndSpa.controller.table;

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
import team2.MoonLightHotelAndSpa.converter.table.TableReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationUpdateRequest;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.table.TableReservationService;
import team2.MoonLightHotelAndSpa.service.table.TableService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/tables")
@Tag(name = "Table Reservation")
@CrossOrigin
public class TableReservationController {

    private final UserService userService;
    private final TableService tableService;
    private final TableReservationConverter tableReservationConverter;
    private final TableReservationService tableReservationService;

    @PostMapping(value = "/{id}/reservations")
    @Operation(summary = "Save table reservation", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<TableReservationResponse> save(@RequestBody TableReservationRequest tableReservationRequest,
                                                         @PathVariable long id,
                                                         @AuthenticationPrincipal User user) {
        User foundUser = userService.findById(user.getId());
        TableReservation tableReservation = tableReservationConverter.convert(tableReservationRequest, id, foundUser);
        TableReservation savedTableReservation = tableReservationService.save(tableReservation);
        TableReservationResponse tableReservationResponse = tableReservationConverter.convert(savedTableReservation);
        return ResponseEntity.ok().body(tableReservationResponse);
    }

    @GetMapping(value = "/{id}/reservations")
    @Operation(summary = "Show all reservation for specific table", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<TableReservationResponse>> findAllReservationsForTable(@PathVariable long id) {
        Table table = tableService.findById(id);
        List<TableReservation> allReservation = tableReservationService.findAllByTable(table.getId());
        List<TableReservationResponse> tableReservationResponses = allReservation.stream().map(tableReservationConverter::convert).toList();
        return ResponseEntity.ok(tableReservationResponses);
    }

    @GetMapping(value = "/{id}/reservations/{rid}")
    @Operation(summary = "Show a reservation by ID and table ID", responses = {
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
    public ResponseEntity<TableReservationResponse> findReservationById(@PathVariable long id,
                                                                        @PathVariable long rid) {
        TableReservation byTableIdAndReservationId = tableReservationService.findByTableIdAndReservationId(id, rid);
        TableReservationResponse convert = tableReservationConverter.convert(byTableIdAndReservationId);
        return ResponseEntity.ok().body(convert);
    }

    @PutMapping(value = "/{id}/reservations/{rid}")
    @Operation(summary = "Update a reservation by ID and Table ID", responses = {
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
    public ResponseEntity<TableReservationResponse> update(@RequestBody TableReservationUpdateRequest tableReservationUpdateRequest, @PathVariable long id, @PathVariable long rid) {
        TableReservation tableReservation = tableReservationConverter.convert(tableReservationUpdateRequest);
        TableReservation updatedTableReservation = tableReservationService.update(tableReservation, id, rid);
        TableReservationResponse tableReservationResponse = tableReservationConverter.convert(updatedTableReservation);
        return ResponseEntity.ok().body(tableReservationResponse);
    }

    @PostMapping(value = "/{id}/summarize")
    @Operation(summary = "Summarize table reservation", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableReservationResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<TableReservationResponse> summarizeTableReservation(@RequestBody TableReservationRequest tableReservationRequest,
                                                                              @PathVariable long id,
                                                                              @AuthenticationPrincipal User user) {
        User foundUser = userService.findById(user.getId());
        TableReservationResponse tableReservationResponse = tableReservationConverter.convertSummarize(tableReservationRequest, id, user);
        return ResponseEntity.ok().body(tableReservationResponse);
    }

    @DeleteMapping(value = "/{id}/reservations/{rid}")
    @Operation(summary = "Delete a reservation by ID and table ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteReservationById(@PathVariable long id, @PathVariable long rid) {
        tableReservationService.tableReservationIdMatch(id, rid);
        tableReservationService.deleteTableReservationById(rid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
