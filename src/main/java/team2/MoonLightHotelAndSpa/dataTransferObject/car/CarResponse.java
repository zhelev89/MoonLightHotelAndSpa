package team2.MoonLightHotelAndSpa.dataTransferObject.car;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryResponse;

import java.util.Set;

@Builder
@Data
public class CarResponse {

    private long id;
    private String brand;
    private String model;
    private String image;
    private Set<String> images;
    private int year;
    private String created;
    private CarCategoryResponse category;
}
