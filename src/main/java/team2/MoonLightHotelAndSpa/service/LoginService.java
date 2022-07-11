package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginResponse;

public interface LoginService {

    LoginResponse authenticate(LoginRequest loginRequest);
}
