package team2.MoonLightHotelAndSpa.converter.screen;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenRequest;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

@Component
@AllArgsConstructor
public class ScreenConverter {

    public Screen convert(ScreenRequest screenRequest) {
        return Screen.builder()
                .title(screenRequest.getTitle())
                .image(screenRequest.getImage())
                .position(screenRequest.getPosition())
                .seats(screenRequest.getSeats())
                .build();
    }
}
