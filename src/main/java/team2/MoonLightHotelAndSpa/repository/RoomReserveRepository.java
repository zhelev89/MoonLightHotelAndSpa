package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.reserve.RoomReserve;

@Repository
public interface RoomReserveRepository extends JpaRepository<RoomReserve, Long> {
}
