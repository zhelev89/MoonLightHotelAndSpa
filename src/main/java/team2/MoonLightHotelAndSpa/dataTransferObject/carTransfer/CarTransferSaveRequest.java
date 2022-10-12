package team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarTransferSaveRequest {

    private String date;
}
