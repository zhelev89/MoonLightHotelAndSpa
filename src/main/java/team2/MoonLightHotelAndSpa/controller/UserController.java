package team2.MoonLightHotelAndSpa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.*;
import team2.MoonLightHotelAndSpa.convertor.UserConverter;
import team2.MoonLightHotelAndSpa.exception.EmailNotSendException;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.EmailSenderService;
import team2.MoonLightHotelAndSpa.service.LoginService;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.dataTransferObjects.ResetPasswordDto;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
@Tag(name = "User")
public class UserController {

    private final UserConverter userConverter;
    private final UserService userService;
    private final LoginService loginService;
    private final EmailSenderService emailSenderService;

    @PostMapping
    @Operation(summary = "Save user")
    public ResponseEntity<UserResponse> save(@RequestBody UserSaveRequest userSaveRequest) {
        User user = userConverter.convert(userSaveRequest);
        String text = String.format("You can access your system with your email: %s and password: %s.", user.getEmail(), user.getPassword());
        User savedUser = userService.save(user);
        emailSenderService.sendEmail(user.getEmail(), "Access to Moonlight Hotel.", text);
        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> token(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.authenticate(loginRequest));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find user by ID")
    public ResponseEntity<UserResponse> findById(@PathVariable @Valid Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<Set<UserResponse>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll().stream()
                        .map(userConverter::convert)
                        .collect(Collectors.toSet()));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        User convertedUser = userConverter.convert(userUpdateRequest);
        User updatedUser = userService.update(id, convertedUser);
        UserResponse userResponse = userConverter.convert(updatedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/reset")
    @Operation(summary = "Reset the password")
    public ResponseEntity<UserResponse> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        User user = userService.changePassword(resetPasswordDto.getNewPassword(), resetPasswordDto.getCurrentPassword(), resetPasswordDto.getEmail());
        UserResponse userResponse = userConverter.convert(user);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping(value = "/forgot")
    @Operation(summary = "Forgot password by email")
    public ResponseEntity<HttpStatus> forgotPassword(@RequestBody EmailForPasswordDto dto) {
        emailSenderService.forgotPassword(dto.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
