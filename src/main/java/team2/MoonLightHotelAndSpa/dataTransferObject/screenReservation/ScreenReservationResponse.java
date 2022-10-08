package team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

@Data
@Builder
public class ScreenReservationResponse {

    private long id;
    private Integer[] seats;
    private String date;
    private double price;
    private Screen screen;
}
