package team2.MoonLightHotelAndSpa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exceptions.DuplicateRecordException;
import team2.MoonLightHotelAndSpa.exceptions.NotFoundRecordException;
import team2.MoonLightHotelAndSpa.models.users.Role;
import team2.MoonLightHotelAndSpa.repositories.RoleRepository;
import team2.MoonLightHotelAndSpa.services.RoleService;

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
            throw new DuplicateRecordException(
                    String.format("UserRole with name: %s, is already exist.", role.getRole()));
        }
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role).orElseThrow(
                () -> new NotFoundRecordException(
                        String.format("Role with name:%s, not found", role)));
    }
}
