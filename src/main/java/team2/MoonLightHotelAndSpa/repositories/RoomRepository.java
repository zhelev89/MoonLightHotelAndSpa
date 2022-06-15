package team2.MoonLightHotelAndSpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLightHotelAndSpa.models.rooms.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
