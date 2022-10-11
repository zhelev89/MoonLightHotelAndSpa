package team2.MoonLightHotelAndSpa.dataTransferObject.screenReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenReservationUpdateRequest {

    private String date;
    private long user;
    private Integer[] seats;
    private double price;
}
