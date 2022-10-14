package team2.MoonLightHotelAndSpa.dataTransferObject.car;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CarSaveRequest {

    private long categoryId;
    private String brand;
    private String model;
    private String image;
    private Set<String> images;
    private int year;
}
