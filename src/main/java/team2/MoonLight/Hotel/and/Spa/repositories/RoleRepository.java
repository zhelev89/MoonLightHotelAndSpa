package team2.MoonLight.Hotel.and.Spa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLight.Hotel.and.Spa.models.users.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);
}
