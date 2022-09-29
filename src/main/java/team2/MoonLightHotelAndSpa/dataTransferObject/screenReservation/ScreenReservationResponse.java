package team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

import java.time.Instant;

@Data
@Builder
public class ScreenReservationResponse {

    private long id;
    private Integer[] seats;
    private Instant date;
    private Screen screen;

}
