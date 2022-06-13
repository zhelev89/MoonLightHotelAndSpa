package team2.MoonLight.Hotel.and.Spa.convertors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserRegisterRequest;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserResponse;
import team2.MoonLight.Hotel.and.Spa.models.users.User;

@Component
@AllArgsConstructor
public class UserConverter {

    public User convert(UserRegisterRequest userRegisterRequest) {
        return User.builder()
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .email(userRegisterRequest.getEmail())
                .phone(userRegisterRequest.getPhone())
                .password(userRegisterRequest.getPassword())
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
