package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
