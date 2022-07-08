package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.*;
import team2.MoonLightHotelAndSpa.convertor.UserConverter;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.EmailSenderService;
import team2.MoonLightHotelAndSpa.service.UserService;
import team2.MoonLightHotelAndSpa.dataTransferObjects.ResetPasswordDto;
import team2.MoonLightHotelAndSpa.service.implement.LoginServiceImpl;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserConverter userConverter;
    private final UserService userService;
    private final LoginServiceImpl loginService;
    private final EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        User user = userConverter.convert(userSaveRequest);
        String text = String.format("You can access your system with your email: %s and password: %s.", user.getEmail(), user.getPassword());
        emailSenderService.sendEmail(user.getEmail(), "Access to Moonlight Hotel.", text);
        User savedUser = userService.save(user);
        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> token(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.authenticate(loginRequest));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Set<UserResponse>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll().stream()
                        .map(userConverter::convert)
                        .collect(Collectors.toSet()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        User convertedUser = userConverter.convert(userUpdateRequest);
        User updatedUser = userService.update(id, convertedUser);
        UserResponse userResponse = userConverter.convert(updatedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<UserResponse> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        User user = userService.changePassword(resetPasswordDto.getNewPassword(), resetPasswordDto.getCurrentPassword(), resetPasswordDto.getEmail());
        UserResponse userResponse = userConverter.convert(user);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<HttpStatus> forgotPassword(@RequestBody EmailForPasswordDto dto) {
        emailSenderService.forgotPassword(dto.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
