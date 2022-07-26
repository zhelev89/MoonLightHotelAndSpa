package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomTitle;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByTitle(RoomTitle roomTitle);
}
