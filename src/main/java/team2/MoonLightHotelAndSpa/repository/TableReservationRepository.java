package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
import team2.MoonLightHotelAndSpa.model.table.Table;

import java.util.List;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {

    List<TableReservation> findAllByTable(Table table);
}
