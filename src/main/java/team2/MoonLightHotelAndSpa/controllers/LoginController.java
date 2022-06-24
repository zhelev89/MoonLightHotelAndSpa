package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.LoginRequest;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.TokenResponse;
import team2.MoonLightHotelAndSpa.services.LoginService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/token")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = loginService.authenticate(loginRequest);
        return ResponseEntity.ok().body(TokenResponse.builder()
                .token(token)
                .build());
    }
}
