package team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.UserResponse;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

@Data
@Builder
public class ScreenReservationResponseV2 {

    private long id;
    private Integer[] seats;
    private String date;
    private double price;
    private Screen screen;
    private UserResponse userResponse;
}
