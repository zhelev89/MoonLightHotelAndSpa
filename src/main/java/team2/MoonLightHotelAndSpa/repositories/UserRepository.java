package team2.MoonLightHotelAndSpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLightHotelAndSpa.models.users.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
