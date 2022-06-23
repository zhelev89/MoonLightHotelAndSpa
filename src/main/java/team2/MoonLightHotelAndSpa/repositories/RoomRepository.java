package team2.MoonLightHotelAndSpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.models.rooms.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
