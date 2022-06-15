package team2.MoonLightHotelAndSpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
}
