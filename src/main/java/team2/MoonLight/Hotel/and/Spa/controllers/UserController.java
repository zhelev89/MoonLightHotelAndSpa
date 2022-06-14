package team2.MoonLight.Hotel.and.Spa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLight.Hotel.and.Spa.convertors.UserConverter;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserSaveRequest;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserResponse;
import team2.MoonLight.Hotel.and.Spa.models.users.User;
import team2.MoonLight.Hotel.and.Spa.services.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserConverter userConverter;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        User user = userConverter.convert(userSaveRequest);
        User savedUser = userService.save(user);
        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.ok().body(userResponse);
    }
}
