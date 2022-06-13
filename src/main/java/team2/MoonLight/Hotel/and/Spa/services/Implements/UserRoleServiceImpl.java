package team2.MoonLight.Hotel.and.Spa.services.Implements;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import team2.MoonLight.Hotel.and.Spa.exceptions.DuplicateRecordException;
import team2.MoonLight.Hotel.and.Spa.models.users.UserRole;
import team2.MoonLight.Hotel.and.Spa.repositories.UserRoleRepository;
import team2.MoonLight.Hotel.and.Spa.services.UserRoleService;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole save(UserRole userRole) {
        try {
            Objects.requireNonNull(userRole);
            return userRoleRepository.save(userRole);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateRecordException(
                    String.format("UserRole with name: %s, is already exist.", userRole.getUserRole()));
        }
    }

    public UserRole findByRole(String userRole) {
        UserRole foundedRole = userRoleRepository.findByUserRole(userRole);
        return foundedRole;
    }
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
