package team2.MoonLightHotelAndSpa.controller.screen;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.service.ScreenReservationService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/screens")
public class ScreenReservationController {

    private final ScreenReservationService screenReservationService;

    @PostMapping(value = "/{id}/reservations")
    private ResponseEntity<ScreenReservation> createReservation() {
        return ResponseEntity.ok().body(screenReservationService.save())
    }
}
