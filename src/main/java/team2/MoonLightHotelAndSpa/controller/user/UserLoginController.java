package team2.MoonLightHotelAndSpa.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.converter.user.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.*;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.user.EmailSenderService;
import team2.MoonLightHotelAndSpa.service.user.LoginService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
@Tag(name = "User")
public class UserLoginController {

    private final LoginService loginService;
    private final UserService userService;
    private final UserConverter userConverter;
    private final EmailSenderService emailSenderService;

    @PostMapping("/token")
    @Operation(summary = "Login", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
    })
    public ResponseEntity<LoginResponse> token(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.authenticate(loginRequest));
    }

    @PostMapping(value = "/reset")
    @Operation(summary = "Reset the password", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class)))
    })
    public ResponseEntity<UserResponse> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        User user = userService.resetPassword(resetPasswordDto.getNewPassword(),
                resetPasswordDto.getCurrentPassword(),
                resetPasswordDto.getEmail());
        UserResponse userResponse = userConverter.convert(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userResponse);
    }

    @PostMapping(value = "/forgot")
    @Operation(summary = "Forgot password by email", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> forgotPassword(@RequestBody EmailForPasswordDto dto) {
        emailSenderService.forgotPassword(dto.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
