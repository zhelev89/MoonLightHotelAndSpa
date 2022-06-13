package team2.MoonLight.Hotel.and.Spa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.MoonLight.Hotel.and.Spa.models.rooms.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
