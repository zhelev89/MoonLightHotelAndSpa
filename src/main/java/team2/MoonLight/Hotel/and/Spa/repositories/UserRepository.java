package team2.MoonLight.Hotel.and.Spa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLight.Hotel.and.Spa.models.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
