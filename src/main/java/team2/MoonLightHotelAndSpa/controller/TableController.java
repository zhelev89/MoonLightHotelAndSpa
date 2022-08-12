package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.TableConverter;
import team2.MoonLightHotelAndSpa.converter.TableReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.table.TableSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.tableReservation.TableReservationResponse;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.security.JwtTokenUtil;
import team2.MoonLightHotelAndSpa.service.TableReservationService;
import team2.MoonLightHotelAndSpa.service.TableService;
import team2.MoonLightHotelAndSpa.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/tables")
@Tag(name = "Table")
public class TableController {

    private final JwtTokenUtil jwtTokenUtil;
    private final TableService tableService;
    private final TableConverter tableConverter;
    private final UserService userService;
    private final TableReservationConverter tableReservationConverter;
    private final TableReservationService tableReservationService;

    @PostMapping
    public ResponseEntity<TableResponse> save(@RequestBody TableSaveRequest tableSaveRequest) {
        Table table = tableConverter.convert(tableSaveRequest);
        Table savedTable = tableService.save(table);
        TableResponse tableResponse = tableConverter.convert(savedTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(tableResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {
        tableService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{id}/reservations")
    public ResponseEntity<TableReservationResponse> save(@RequestBody TableReservationRequest tableReservationRequest, @PathVariable long id, @AuthenticationPrincipal User user) {
        User foundUser = userService.findById(user.getId());
        TableReservation tableReservation = tableReservationConverter.convert(tableReservationRequest, id, foundUser);
        TableReservation savedTableReservation = tableReservationService.save(tableReservation);
        TableReservationResponse tableReservationResponse = tableReservationConverter.convert(savedTableReservation);
        return ResponseEntity.ok().body(tableReservationResponse);
    }
}
