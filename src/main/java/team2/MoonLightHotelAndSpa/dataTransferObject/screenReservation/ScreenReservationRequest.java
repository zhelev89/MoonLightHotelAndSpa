package team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class ScreenReservationRequest {

    private Instant date;
    private Integer[] seats;
}
