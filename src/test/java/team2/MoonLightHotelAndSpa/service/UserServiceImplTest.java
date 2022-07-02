package team2.MoonLightHotelAndSpa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.exceptions.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.repositories.UserRepository;
import team2.MoonLightHotelAndSpa.services.Implements.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @Autowired
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void verifySave() {
        String password = "password";
        User user = User.builder()
                .password(password)
                .build();
        userService.save(user);
        verify(userRepository, times(1)).save(user);
        assertEquals(bCryptPasswordEncoder.encode(password), user.getPassword());
    }

    @Test
    public void verifyFindById() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(User.builder().build()));
        userService.findById(id);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void verifyFindByIdThrowsException() {
        String message = "User with id:1, not found.";
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class, () -> {
            userService.findById(1L);
        });
        assertEquals(message, recordNotFoundException.getMessage());
    }

    @Test
    public void verifyFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(User.builder().build()));
        userService.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void verifyUpdate() {
        User user = User.builder()
                .id(1L)
                .name("Georgi")
                .surname("Georgiev")
                .phone("0888888888")
                .email("email@gmail.com")
                .password("12345678")
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(User.builder()
                .id(1L)
                .name("Georgi")
                .surname("Georgiev")
                .phone("0888888888")
                .email("email@gmail.com")
                .password("12345678")
                .build()));
        User returnedUser = userService.update(user.getId(), user);
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getName(), returnedUser.getName());
        assertEquals(user.getSurname(), returnedUser.getSurname());
        assertEquals(user.getPhone(), returnedUser.getPhone());
        assertEquals(user.getEmail(), returnedUser.getEmail());
    }
}
