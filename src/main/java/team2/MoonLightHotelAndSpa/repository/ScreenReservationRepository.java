package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;
import team2.MoonLightHotelAndSpa.model.screen.Screen;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.time.Instant;
import java.util.List;

public interface ScreenReservationRepository extends JpaRepository<ScreenReservation, Long> {

    List<ScreenReservation> findByUser(User user);

    List<ScreenReservation> findAllByScreen(Screen screen);

    @Query(value = "SELECT sc FROM ScreenReservation sc " +
            "WHERE sc.screen.id = :id " +
            "AND sc.date = :date")
    List<ScreenReservation> findByScreenIdAndDate(long id, Instant date);
}
