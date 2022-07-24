package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
    Set<RoomReservation> findAllByUser(User user);

    @Query("SELECT r FROM r WHERE r.id NOT IN rr.room")
    List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people);
}
