package team2.MoonLightHotelAndSpa.controller.screen;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.screen.ScreenReservationConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.*;
import team2.MoonLightHotelAndSpa.model.screen.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.screen.ScreenReservationService;
import team2.MoonLightHotelAndSpa.service.screen.ScreenService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/screens")
public class ScreenReservationController {

    private final ScreenReservationService screenReservationService;
    private final ScreenReservationConverter screenReservationConverter;
    private final ScreenService screenService;
    private final UserService userService;

    @PostMapping(value = "/{id}/summarize")
    public ResponseEntity<List<Integer>> findFreeSeatsByScreenIdAndDate(@PathVariable long id,
                                                                        @RequestBody ScreenRequestFindFreeSeats screenRequestFindFreeSeats) {
        List<Integer> freeSeats =
                screenReservationService.findFreeSeatsByScreenIdAndDate(id, screenRequestFindFreeSeats.getDate());
        return ResponseEntity.ok().body(freeSeats);
    }

    @PostMapping(value = "/{id}/reservations")
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
    private ResponseEntity<List<ScreenReservationResponse>> findAllByScreenId(@PathVariable long id) {
        List<ScreenReservation> allByScreenId = screenReservationService.findAllByScreenId(id);
        return ResponseEntity.ok().body(allByScreenId.stream()
                .map(screenReservationConverter::convert)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}/reservations/{rid}")
    private ResponseEntity<ScreenReservationResponseV2> findByIdAndRid(@PathVariable long id,
                                                                       @PathVariable long rid) {
        ScreenReservation screenReservation = screenReservationService.findByScreenIdAndReservationId(id, rid);
        ScreenReservationResponseV2 responseV2 = screenReservationConverter.convert(screenReservation, screenReservation.getUser());
        return ResponseEntity.ok().body(responseV2);
    }

    @PutMapping(value = "/{id}/reservations/{rid}")
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
    private ResponseEntity<HttpStatus> deleteByScreenIdAndReservationId(@PathVariable long id, @PathVariable long rid) {
        screenReservationService.deleteByScreenIdAndReservationsId(id, rid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
