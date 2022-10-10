package team2.MoonLightHotelAndSpa.controller.table;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.table.TableReservationConverter;
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
public class TableReservationController {

    private final UserService userService;
    private final TableService tableService;
    private final TableReservationConverter tableReservationConverter;
    private final TableReservationService tableReservationService;

    @PostMapping(value = "/{id}/reservations")
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
    public ResponseEntity<List<TableReservationResponse>> findAllReservationsForTable(@PathVariable long id) {
        Table table = tableService.findById(id);
        List<TableReservation> allReservation = tableReservationService.findAllByTable(table.getId());
        List<TableReservationResponse> tableReservationResponses = allReservation.stream().map(tableReservationConverter::convert).toList();
        return ResponseEntity.ok(tableReservationResponses);
    }

    @GetMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<TableReservationResponse> findReservationById(@PathVariable long id,
                                                                        @PathVariable long rid) {
        TableReservation byTableIdAndReservationId = tableReservationService.findByTableIdAndReservationId(id, rid);
        TableReservationResponse convert = tableReservationConverter.convert(byTableIdAndReservationId);
        return ResponseEntity.ok().body(convert);
    }

    @PutMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<TableReservationResponse> update(@RequestBody TableReservationUpdateRequest tableReservationUpdateRequest, @PathVariable long id, @PathVariable long rid) {
        TableReservation tableReservation = tableReservationConverter.convert(tableReservationUpdateRequest);
        TableReservation updatedTableReservation = tableReservationService.update(tableReservation, id, rid);
        TableReservationResponse tableReservationResponse = tableReservationConverter.convert(updatedTableReservation);
        return ResponseEntity.ok().body(tableReservationResponse);
    }

    @PostMapping(value = "/{id}/summarize")
    public ResponseEntity<TableReservationResponse> summarizeTableReservation(@RequestBody TableReservationRequest tableReservationRequest,
                                                                              @PathVariable long id,
                                                                              @AuthenticationPrincipal User user) {
        User foundUser = userService.findById(user.getId());
        TableReservationResponse tableReservationResponse = tableReservationConverter.convertSummarize(tableReservationRequest, id, user);
        return ResponseEntity.ok().body(tableReservationResponse);
    }

    @DeleteMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<HttpStatus> deleteReservationById(@PathVariable long id, @PathVariable long rid) {
        tableReservationService.tableReservationIdMatch(id, rid);
        tableReservationService.deleteTableReservationById(rid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
