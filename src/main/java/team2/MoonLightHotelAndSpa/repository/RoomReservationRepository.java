package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.util.Set;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

    Set<RoomReservation> findAllByUser(User user);
}
