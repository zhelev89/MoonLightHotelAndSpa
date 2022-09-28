package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenSaveRequest;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

@Component
@AllArgsConstructor
public class ScreenConverter {

    public Screen convert(ScreenSaveRequest screenSaveRequest) {
        return Screen.builder()
                .title(screenSaveRequest.getTitle())
                .image(screenSaveRequest.getImage())
                .position(screenSaveRequest.getPosition())
                .seats(screenSaveRequest.getSeats())
                .build();
    }
}
