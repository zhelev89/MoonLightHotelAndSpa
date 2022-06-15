package team2.MoonLightHotelAndSpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLightHotelAndSpa.models.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
