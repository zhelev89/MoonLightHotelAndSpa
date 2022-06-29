package team2.MoonLightHotelAndSpa.convertors;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class RoleConverter {

    private static final String role_prefix = "ROLE_";

    public String convertRoleRequest(String role) {
        if (!role.startsWith(role_prefix)) {
            return "ROLE_" + role.toUpperCase(Locale.ROOT);
        }
        return role.toUpperCase();
    }

    public String convertToRoleResponse(String role) {
        return role.substring(5).toLowerCase(Locale.ROOT);
    }
}
