package team2.MoonLightHotelAndSpa.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.converter.user.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.LoginResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.user.UserResponse;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.security.JwtTokenUtil;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserConverter userConverter;

    public LoginResponse authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new RecordBadRequestException("Invalid Credentials.");
        }

        User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
        UserResponse userResponse = userConverter.convert(user);

        return LoginResponse.builder()
                .token(jwtTokenUtil.generateToken(user))
                .user(userResponse)
                .build();
    }
}
