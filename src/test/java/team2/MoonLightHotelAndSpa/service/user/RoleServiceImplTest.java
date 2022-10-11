package team2.MoonLightHotelAndSpa.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.user.Role;
import team2.MoonLightHotelAndSpa.repository.RoleRepository;
import team2.MoonLightHotelAndSpa.service.user.RoleServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    private RoleServiceImpl roleService;

    @BeforeEach
    public void setUp() {
        roleService = new RoleServiceImpl(roleRepository);
    }

    @Test
    public void verifySave() {
        Role role = Role.builder().build();
        roleService.save(role);
        verify(roleRepository , times(1)).save(role);
    }

    @Test
    public void verifyFindByRole() {
        String role = "Admin";
        when(roleRepository.findByRole(role)).thenReturn(Optional.of(Role.builder().build()));
        roleService.findByRole(role);
        verify(roleRepository, times(1)).findByRole(role);
    }

    @Test
    public void verifyFindByRoleThrowsException() {
        String message = "Role with name:Client, not found";
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            roleService.findByRole("Client");
        });
        assertEquals(message, exception.getMessage());
    }
}
