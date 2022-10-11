package team2.MoonLightHotelAndSpa.service.room;

import org.springframework.http.ResponseEntity;
import team2.MoonLightHotelAndSpa.model.reservation.RoomReservation;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;
import team2.MoonLightHotelAndSpa.model.room.RoomView;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    RoomReservation findById(long id);

    RoomReservation findByUserIdAndReservationId(long uid, long rid);

    Set<RoomReservation> findAllByUserId(long id);

    Set<RoomReservation> findAll();

    int calculateDays(Instant startDate, Instant endDate);

    void deleteById(long id);

    void roomReservationIdMatch(long roomId, long roomReservationId);

    RoomReservation update(long id, long rid, RoomReservation updatedRoomReservation);

    List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people);

    void isPaid(long reservationId);

    ResponseEntity<?> findAllAvailableRoomsDetailed(String start_date, String end_date, int adults, int kids, RoomView roomView, RoomTitle roomTitle);
}
