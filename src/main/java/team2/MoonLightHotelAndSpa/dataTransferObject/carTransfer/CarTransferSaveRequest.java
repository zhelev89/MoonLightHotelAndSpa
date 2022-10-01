package team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarTransferSaveRequest {

    private String date;
    private int seats;
    private String model;
}
