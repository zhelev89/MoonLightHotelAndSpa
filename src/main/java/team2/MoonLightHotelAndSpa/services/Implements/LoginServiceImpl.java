package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.LoginRequest;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.LoginService;
import team2.MoonLightHotelAndSpa.utils.JsonWebTokenUtil;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JsonWebTokenUtil jsonWebTokenUtil;

    public String authenticate(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User user = (User) authentication.getPrincipal();

        return jsonWebTokenUtil.generateToken(user);
    }
}
