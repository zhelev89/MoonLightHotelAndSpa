package team2.MoonLightHotelAndSpa.service.screen;

import team2.MoonLightHotelAndSpa.dataTransferObject.screen.ScreenRequest;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

public interface ScreenService {

    Screen save(Screen screen);

    Screen findById(long id);

    Screen update(long id, ScreenRequest screenRequest);

    void delete(long id);
}
