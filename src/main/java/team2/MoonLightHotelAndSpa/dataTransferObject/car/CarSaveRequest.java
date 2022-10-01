package team2.MoonLightHotelAndSpa.dataTransferObject.car;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.car.CarImage;

import java.util.Set;

@Builder
@Data
public class CarSaveRequest {

    private long categoryId;
    private String brand;
    private String model;
    private String image;
    private Set<CarImage> images;
    private int year;
}
