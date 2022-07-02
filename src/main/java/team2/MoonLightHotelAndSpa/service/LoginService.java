package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginRequest;

public interface LoginService {

    String authenticate(LoginRequest loginRequest);
}
