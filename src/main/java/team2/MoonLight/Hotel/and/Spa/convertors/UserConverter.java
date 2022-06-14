package team2.MoonLight.Hotel.and.Spa.convertors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserSaveRequest;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserResponse;
import team2.MoonLight.Hotel.and.Spa.models.users.User;

@Component
@AllArgsConstructor
public class UserConverter {

    public User convert(UserSaveRequest userSaveRequest) {
        return User.builder()
                .firstName(userSaveRequest.getFirstName())
                .lastName(userSaveRequest.getLastName())
                .email(userSaveRequest.getEmail())
                .phone(userSaveRequest.getPhone())
                .password(userSaveRequest.getPassword())
                .build();
    }

    public UserResponse convert(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
