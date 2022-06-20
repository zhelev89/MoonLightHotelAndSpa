package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.dataTransferObjects.LoginRequest;

public interface LoginService {

    void authenticate(LoginRequest loginRequest);
}
