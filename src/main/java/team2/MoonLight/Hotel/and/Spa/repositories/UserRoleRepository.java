package team2.MoonLight.Hotel.and.Spa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLight.Hotel.and.Spa.models.users.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserRole(String userRole);
}
