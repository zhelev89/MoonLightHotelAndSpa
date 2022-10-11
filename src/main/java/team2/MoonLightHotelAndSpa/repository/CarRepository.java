package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.car.Car;

import java.time.Instant;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    //    @Query("SELECT r, COUNT(*) FROM Room r " +
//            "WHERE r.people >= :people " +
//            "AND r.id NOT IN (SELECT rr.room FROM RoomReservation rr) " +
//            "OR r.id IN (SELECT rrIn.room FROM RoomReservation rrIn " +
//            "WHERE rrIn.startDate NOT BETWEEN :start_date AND :end_date " +
//            "AND rrIn.endDate NOT BETWEEN :start_date AND :end_date) " +
//            "GROUP BY r HAVING COUNT(*) <= r.count")


//    @Query(value = "SELECT sc FROM ScreenReservation sc " +
//            "WHERE sc.screen.id = :id " +
//            "AND sc.date = :date")

//    @Query("SELECT c FROM Car c WHERE c.carCategory.seats >= :seats AND c.id NOT IN (SELECT ct.car FROM CarTransfer ct) OR c.id IN (SELECT ct.car FROM CarTransfer ct WHERE ct.date != :date)")
    @Query("SELECT c FROM Car c WHERE c.carCategory.seats >= :seats AND c NOT IN (SELECT ct.car FROM CarTransfer ct) OR c IN (SELECT ct.car FROM CarTransfer ct WHERE ct.date != :date)")
    List<Car> findAllAvailableCars(Instant date, int seats);
}
// if(c.getCarCategory.geSeats >= seats && c_id = ct.car_id || ct.getDate != :date)