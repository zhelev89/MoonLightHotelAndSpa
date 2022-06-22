package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserUpdateRequest;
import team2.MoonLightHotelAndSpa.convertors.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserSaveRequest;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        User user = userConverter.convert(userSaveRequest);
        User savedUser = userService.save(user);
        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @Valid Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<Set<User>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.findAll());
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        User user = userConverter.convert(userUpdateRequest);
        User updatedUser = userService.update(user.getId(), user.getPassword());
        UserResponse userResponse = userConverter.convert(updatedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
