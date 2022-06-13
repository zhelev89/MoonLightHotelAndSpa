package team2.MoonLight.Hotel.and.Spa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLight.Hotel.and.Spa.models.reservations.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
}
