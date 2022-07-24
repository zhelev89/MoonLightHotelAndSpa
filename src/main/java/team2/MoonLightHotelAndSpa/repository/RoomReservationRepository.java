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

    @Query("SELECT r, COUNT(*) FROM Room r " +
            "WHERE r.people >= :people " +
            "AND r.id NOT IN " +
            "(SELECT rr.room " +
            "FROM RoomReservation rr) " +
            "AND r.id IN (SELECT rrm.room " +
            "FROM RoomReservation rrm " +
            "WHERE " +
            "rrm.startDate NOT BETWEEN :start AND :end " +
            "AND rrm.endDate NOT BETWEEN :start AND :end) " +
            "GROUP BY r HAVING COUNT(*) <= r.count")
    List<Room> findAllAvailableRooms(Instant start, Instant end, int people);
}