package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.LoginRequest;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/token")
public class LoginController {

    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        return null;
    }
}
