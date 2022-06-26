package team2.MoonLightHotelAndSpa.convertors;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class RoleConverter {

    public String convertRoleRequest(String role) {
        return "ROLE_" + role.toUpperCase(Locale.ROOT);
    }

    public String convertToRoleResponse(String role) {
        return role.substring(5).toLowerCase(Locale.ROOT);
    }
}
