package team2.MoonLight.Hotel.and.Spa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLight.Hotel.and.Spa.convertors.UserConverter;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserSaveRequest;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserResponse;
import team2.MoonLight.Hotel.and.Spa.models.users.User;
import team2.MoonLight.Hotel.and.Spa.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @Valid Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
    }

    //Тук мапинга не работи... получава се инфинити лууп, който трябва да се оправи
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.findAll().stream()
                        .map(user -> userConverter.convert(user))
                        .collect(Collectors.toList()));
    }
}
