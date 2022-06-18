package team2.MoonLightHotelAndSpa.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.UserUpdateRequest;
import team2.MoonLightHotelAndSpa.convertors.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObjects.UserResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.UserSaveRequest;
import team2.MoonLightHotelAndSpa.models.users.Role;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.RoleService;
import team2.MoonLightHotelAndSpa.services.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserSaveRequest userSaveRequest) {
        Role customer = roleService.findByRole("Client");
        customer.setUsers(new HashSet<>());

        userSaveRequest.setRoles(new HashSet<>());
        userSaveRequest.getRoles().add(customer);

        User user = userConverter.convert(userSaveRequest);
        User savedUser = userService.save(user);

        UserResponse userResponse = userConverter.convert(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @Valid Long id) {
        User foundUser = userService.findById(id);
        UserResponse userResponse = userConverter.convert(foundUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.findAll().stream()
                        .map(user -> userConverter.convert(user))
                        .collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        User user = userConverter.convert(userUpdateRequest);
        User updatedUser = userService.update(user.getId(), user.getPassword());
        UserResponse userResponse = userConverter.convert(updatedUser);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
