package team2.MoonLightHotelAndSpa.dataTransferObject.carCategory;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.car.CarTitle;

@Builder
@Data
public class CarCategorySaveRequest {

    private CarTitle title;
    private int seats;
    private double price;
    private int position;
}
