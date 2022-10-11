package team2.MoonLightHotelAndSpa.converter.screen;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.converter.user.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation.ScreenReservationResponseV2;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.time.Instant;

@Component
@AllArgsConstructor
public class ScreenReservationConverter {

    private final UserConverter userConverter;

    public ScreenReservation convert(ScreenReservationRequest screenReservationRequest) {
        return ScreenReservation.builder()
                .date(Instant.parse(screenReservationRequest.getDate()))
                .seats(screenReservationRequest.getSeats())
                .build();
    }

    public ScreenReservationResponse convert(ScreenReservation screenReservation) {
        return ScreenReservationResponse.builder()
                .id(screenReservation.getId())
                .seats(screenReservation.getSeats())
                .date(screenReservation.getDate().toString())
                .price(screenReservation.getPrice())
                .screen(screenReservation.getScreen())
                .build();
    }

    public ScreenReservationResponseV2 convert(ScreenReservation screenReservation, User user) {
        return ScreenReservationResponseV2.builder()
                .id(screenReservation.getId())
                .seats(screenReservation.getScreen().getSeats())
                .date(screenReservation.getDate().toString())
                .price(screenReservation.getPrice())
                .screen(screenReservation.getScreen())
                .userResponse(userConverter.convert(user))
                .build();
    }
}
