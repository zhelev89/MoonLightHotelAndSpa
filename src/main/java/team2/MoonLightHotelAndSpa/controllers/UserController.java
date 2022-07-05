package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.MoonLightHotelAndSpaApplication;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserUpdateRequest;
import team2.MoonLightHotelAndSpa.convertors.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserSaveRequest;
import team2.MoonLightHotelAndSpa.exceptions.EmailNotSendException;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.EmailSenderService;
import team2.MoonLightHotelAndSpa.services.UserService;

import javax.validation.Valid;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserConverter userConverter;
    private final UserService userService;
    private final EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        User user = userConverter.convert(userSaveRequest);
        String text = String.format("You can access your system with your email: %s and password: %s.", user.getEmail(), user.getPassword());
        User savedUser = userService.save(user);
        try {
            emailSenderService.sendEmail(user.getEmail(), "Access to Moonlight Hotel.", text);
        }catch (EmailNotSendException ex) {
            throw new EmailNotSendException("Failed to send email");
        }
        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @Valid Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<Set<UserResponse>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll().stream()
                        .map(userConverter::convert)
                        .collect(Collectors.toSet()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        User convertedUser = userConverter.convert(userUpdateRequest);
        User updatedUser = userService.update(id, convertedUser);
        UserResponse userResponse = userConverter.convert(updatedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
