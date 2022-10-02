package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLightHotelAndSpa.model.screen.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.screen.Screen;

import java.time.Instant;
import java.util.List;

public interface ScreenReservationRepository extends JpaRepository<ScreenReservation, Long> {

    List<ScreenReservation> findAllByScreen(Screen screen);

    ScreenReservation findBySeats(Instant date);
}
