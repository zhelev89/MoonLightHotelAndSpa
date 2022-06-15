package team2.MoonLight.Hotel.and.Spa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.MoonLight.Hotel.and.Spa.exceptions.DuplicateRecordException;
import team2.MoonLight.Hotel.and.Spa.exceptions.NotFoundRecordException;
import team2.MoonLight.Hotel.and.Spa.models.users.Role;
import team2.MoonLight.Hotel.and.Spa.repositories.RoleRepository;
import team2.MoonLight.Hotel.and.Spa.services.RoleService;

import java.util.List;
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

    @Override
    public List<Role> findAll() {
        return null;
    }
}
