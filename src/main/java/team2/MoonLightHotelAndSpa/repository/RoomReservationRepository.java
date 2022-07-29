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
            "AND r.id NOT IN (SELECT rr.room FROM RoomReservation rr) " +
            "OR r.id IN (SELECT rrIn.room FROM RoomReservation rrIn " +
            "WHERE rrIn.startDate NOT BETWEEN :start_date AND :end_date " +
            "AND rrIn.endDate NOT BETWEEN :start_date AND :end_date) " +
            "GROUP BY r HAVING COUNT(*) <= r.count")
    List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people);
}