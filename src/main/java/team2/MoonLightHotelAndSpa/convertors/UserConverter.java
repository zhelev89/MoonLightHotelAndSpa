package team2.MoonLightHotelAndSpa.convertors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObjects.UserResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.UserSaveRequest;
import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserConverter {

    private final RoleConverter roleConverter;

    public User convert(UserSaveRequest userSaveRequest) {
        return User.builder()
                .firstName(userSaveRequest.getFirstName())
                .lastName(userSaveRequest.getLastName())
                .email(userSaveRequest.getEmail())
                .phone(userSaveRequest.getPhone())
                .password(userSaveRequest.getPassword())
                .roles(userSaveRequest.getRoles())
                .build();
    }

    public UserResponse convert(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roles(user.getRoles().stream().map(roleConverter::convert).collect(Collectors.toSet()))
                .build();
    }

    public User convert(UserUpdateRequest userUpdateRequest) {
        return User.builder()
                .id(userUpdateRequest.getId())
                .password(userUpdateRequest.getPassword())
                .build();
    }
}
