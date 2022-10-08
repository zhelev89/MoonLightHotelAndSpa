package team2.MoonLightHotelAndSpa.service.user;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordBadRequestException;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.user.Role;
import team2.MoonLightHotelAndSpa.repository.RoleRepository;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public Role save(Role role) {
        try {
            Objects.requireNonNull(role);
            return roleRepository.save(role);
        } catch (DataIntegrityViolationException ex) {
            throw new RecordBadRequestException(
                    String.format("UserRole with name: %s, is already exist.", role.getRole()));
        }
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role).orElseThrow(
                () -> new RecordNotFoundException(
                        String.format("Role with name:%s, not found", role)));
    }
}
