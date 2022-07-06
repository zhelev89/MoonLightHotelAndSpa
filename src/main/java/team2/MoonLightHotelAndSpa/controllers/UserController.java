package team2.MoonLightHotelAndSpa.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
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
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserConverter userConverter;
    private final UserService userService;
    private final EmailSenderService emailSenderService;

    @PostMapping
    @ApiOperation(value = "Save user", notes = "Use this endpoint to save a user in DB.", response = UserResponse.class)
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        User user = userConverter.convert(userSaveRequest);
        String text = String.format("You can access your system with your email: %s and password: %s.", user.getEmail(), user.getPassword());
        try {
            emailSenderService.sendEmail(user.getEmail(), "Access to Moonlight Hotel.", text);
        }catch (EmailNotSendException ex) {
            throw new EmailNotSendException("Failed to send email");
        }
        User savedUser = userService.save(user);
        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(value = "/id/{id}")
    @ApiOperation(value = "Find user by ID", notes = "Use this endpoint to find a user by ID.", response = UserResponse.class)
    public ResponseEntity<UserResponse> findById(@PathVariable @Valid Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    @ApiOperation(value = "Find all users", notes = "Use this endpoint to find all users.", response = UserResponse.class)
    public ResponseEntity<Set<UserResponse>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll().stream()
                        .map(userConverter::convert)
                        .collect(Collectors.toSet()));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update user", notes = "Use this endpoint to update a user.", response = UserResponse.class)
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        User convertedUser = userConverter.convert(userUpdateRequest);
        User updatedUser = userService.update(id, convertedUser);
        UserResponse userResponse = userConverter.convert(updatedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete user", notes = "Use this endpoint to delete a user by id.", response = ResponseEntity.class)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
