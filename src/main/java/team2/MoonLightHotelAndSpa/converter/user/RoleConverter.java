package team2.MoonLightHotelAndSpa.converter.user;

import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class RoleConverter {

    private final String ROLE = "ROLE_";

    public String convertRoleRequest(String role) {
        if (!role.toUpperCase().startsWith(ROLE)) {
            return ROLE + role.toUpperCase(Locale.ROOT);
        }
        return role.toUpperCase();
    }

    public String convertToRoleResponse(String role) {
        return role.substring(5).toLowerCase(Locale.ROOT);
    }
}
