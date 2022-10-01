package team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.UserResponse;

@Builder
@Data
public class CarTransferResponse {

    private long id;
    private double price;
    private String date;
    private String created;
    private CarResponse car;
    private UserResponse user;
}
