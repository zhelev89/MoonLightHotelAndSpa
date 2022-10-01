package team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarTransferUpdateRequest {

    private String date;
    private int seats;
    private long userId;
}
