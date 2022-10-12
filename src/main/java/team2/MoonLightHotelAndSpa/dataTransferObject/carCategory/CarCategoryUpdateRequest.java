package team2.MoonLightHotelAndSpa.dataTransferObject.carCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team2.MoonLightHotelAndSpa.model.car.CarTitle;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCategoryUpdateRequest {

    private CarTitle title;
    private int seats;
    private double price;
    private int position;
}
