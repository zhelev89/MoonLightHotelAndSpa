package team2.MoonLightHotelAndSpa.convertor;

import lombok.AllArgsConstructor;
import java.util.Locale;

@AllArgsConstructor
public class RoleConverter {

    private static final String ROLE_PREFIX = "ROLE_";

    public static String convertRoleRequest(String role) {
        if (!role.toUpperCase().startsWith(ROLE_PREFIX)) {
            return ROLE_PREFIX + role.toUpperCase(Locale.ROOT);
        }
        return role.toUpperCase();
    }

    public static String convertToRoleResponse(String role) {
        return role.substring(5).toLowerCase(Locale.ROOT);
    }
}
