package team2.MoonLightHotelAndSpa.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.convertors.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.LoginRequest;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.TokenResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserResponse;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.LoginService;
import team2.MoonLightHotelAndSpa.services.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/token")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    @ApiOperation(value = "Login", notes = "Use this endpoint to login.", response = TokenResponse.class)
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
