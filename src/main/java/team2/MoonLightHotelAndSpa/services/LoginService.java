package team2.MoonLightHotelAndSpa.services;

import team2.MoonLightHotelAndSpa.dataTransferObjects.users.LoginRequest;

public interface LoginService {

    String authenticate(LoginRequest loginRequest);
}
