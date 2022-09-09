package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.reservation.TableReservation;
<<<<<<< HEAD
import team2.MoonLightHotelAndSpa.model.table.Table;

import java.util.List;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {

    List<TableReservation> findByTable(Table table);
=======

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {
>>>>>>> c8a52e44c4e44bafb1c3e2ebf41029855617ed50
}
