package team2.MoonLightHotelAndSpa.dataTransferObject.screen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import team2.MoonLightHotelAndSpa.model.screen.ScreenPosition;

@Data
@Builder
@AllArgsConstructor
public class ScreenRequest {

    private String title;
    private String image;
    private ScreenPosition position;
    private Integer[] seats;
}
