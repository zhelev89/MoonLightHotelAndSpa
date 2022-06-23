package team2.MoonLightHotelAndSpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.models.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
