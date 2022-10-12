package team2.MoonLightHotelAndSpa.dataTransferObject.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateRequest {

    private long categoryId;
    private String brand;
    private String model;
    private String image;
    private Set<String> images;
    private int year;
}
