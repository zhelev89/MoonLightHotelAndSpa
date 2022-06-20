package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.dataTransferObjects.LoginRequest;
import team2.MoonLightHotelAndSpa.services.LoginService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/token")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        loginService.authenticate(loginRequest);
        return null;
    }
}
