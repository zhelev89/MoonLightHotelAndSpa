package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.screen.Screen;

public interface ScreenService {

    Screen save(Screen screen);

    Screen findById(long id);
}
