package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.convertor.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.TokenResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.UserResponse;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.LoginService;
import team2.MoonLightHotelAndSpa.service.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/token")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = loginService.authenticate(loginRequest);

        User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
        UserResponse userResponse = userConverter.convert(user);
        return ResponseEntity.ok().body(TokenResponse.builder()
                .token(token)
                .user(userResponse)
                .build());
    }
}
