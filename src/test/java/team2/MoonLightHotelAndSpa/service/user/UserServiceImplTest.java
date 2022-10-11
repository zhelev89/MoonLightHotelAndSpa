package team2.MoonLightHotelAndSpa.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.MoonLightHotelAndSpa.exception.PasswordNotMatchingException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.user.Role;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.repository.UserRepository;
import team2.MoonLightHotelAndSpa.service.user.UserServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void verifySave() {
        String password = "password";
        User user = User.builder()
                .password(password)
                .build();
        Objects.requireNonNull(user);
        userService.save(user);
        bCryptPasswordEncoder.matches(password, user.getPassword());
        verify(userRepository, times(1)).save(user);
        boolean isMatching = bCryptPasswordEncoder.matches(password, user.getPassword());
        assertTrue(isMatching);
    }

    @Test
    public void verifyFindById() {
        Long id = 1L;
        Objects.requireNonNull(id);
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
                .roles(Set.of(Role.builder().build()))
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(User.builder()
                .id(1L)
                .name("Georgi")
                .surname("Georgiev")
                .phone("0888888888")
                .email("email@gmail.com")
                .password("12345678")
                .roles(Set.of(Role.builder().build()))
                .build()));
        User returnedUser = userService.update(user.getId(), user);
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getName(), returnedUser.getName());
        assertEquals(user.getSurname(), returnedUser.getSurname());
        assertEquals(user.getPhone(), returnedUser.getPhone());
        assertEquals(user.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void verifyFindByEmail() {
        String email = "email@gamil.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(User.builder().build()));
        userService.findByEmail(email);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void verifyFindByEmailThrowsException() {
        String message = "User with email:email@gamil.com, not found.";
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class, () -> {
            userService.findByEmail("email@gamil.com");
        });
        assertEquals(message, recordNotFoundException.getMessage());
    }

    @Test
    public void verifyDeleteById() {
        User user = User.builder()
                .id(1L)
                .build();
        userService.deleteById(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    public void verifyChangePassword() {
        String email = "gmail@gmail.com";
        String newPassword = "87654321";
        String password = "12345678";
        String currentHashedPassword = bCryptPasswordEncoder.encode(password);
        User user = User.builder()
                .email(email)
                .password(currentHashedPassword)
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        User changedUser = userService.resetPassword(newPassword, password, email);
        boolean isMatching = bCryptPasswordEncoder.matches(newPassword, changedUser.getPassword());
        assertTrue(isMatching);
    }

    @Test
    public void verifyChangePasswordThrowsException() {
        String password = bCryptPasswordEncoder.encode("567");
        when(userRepository.findByEmail("email@gmail.com")).thenReturn(Optional.of(User.builder()
                .email("email@gmail.com")
                .password(password)
                .build()));
        String exceptionMessage = "Your old password does not match";
        PasswordNotMatchingException exception = assertThrows(PasswordNotMatchingException.class, () -> {
            userService.resetPassword("123", "321", "email@gmail.com");
        });
        assertEquals(exception.getMessage(), exceptionMessage);
    }
}
