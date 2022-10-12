package team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer;

import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;

@Builder
@Data
public class SummarizeCarTransfer {

    private long id;
    private CarCategory category;
    private String brand;
    private String model;
    private int seats;
    private double price;
    private String date;
}
