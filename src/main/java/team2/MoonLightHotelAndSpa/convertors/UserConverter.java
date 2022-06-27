package team2.MoonLightHotelAndSpa.convertors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.users.UserSaveRequest;
import team2.MoonLightHotelAndSpa.models.users.Role;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.RoleService;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserConverter {

    private final RoleConverter roleConverter;
    private final RoleService roleService;

    public User convert(UserSaveRequest userSaveRequest) {
        String role = "";
        for (String name : userSaveRequest.getRoles()) {
            role = name;
        }

        role = roleConverter.convertRoleRequest(role);
        Role foundRole = roleService.findByRole(role);

        Set<Role> roles = new HashSet<>();
        roles.add(foundRole);

        return User.builder()
                .name(userSaveRequest.getName())
                .surname(userSaveRequest.getSurname())
                .phone(userSaveRequest.getPhone())
                .email(userSaveRequest.getEmail())
                .password(userSaveRequest.getPassword())
                .roles(roles)
                .build();
    }

    public UserResponse convert(User user) {
        String role = "";
        for (Role roles : user.getRoles()) {
            role = roles.getRole();
        }

        role = roleConverter.convertToRoleResponse(role);
        Set<String> roles = new HashSet<>();
        roles.add(role);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .roles(roles)
                .created(user.getCreated().toString())
                .build();
    }

    public User convert(UserUpdateRequest userUpdateRequest) {

        String role = "";
        for (String name : userUpdateRequest.getRoles()) {
            role = name;
        }

        Role foundRole = roleService.findByRole(role.toLowerCase(Locale.ROOT));
        foundRole.setRole(role.toLowerCase());

        Set<Role> roles = new HashSet<>();
        roles.add(foundRole);

        return User.builder()
                .email(userUpdateRequest.getEmail())
                .password(userUpdateRequest.getPassword())
                .name(userUpdateRequest.getName())
                .surname(userUpdateRequest.getSurname())
                .phone(userUpdateRequest.getPhone())
                .roles(roles)
                .build();
    }
}
