package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLightHotelAndSpa.model.reservation.ScreenReservation;

public interface ScreenReservationRepository extends JpaRepository<ScreenReservation, Long> {
}
