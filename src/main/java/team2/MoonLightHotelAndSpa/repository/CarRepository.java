package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.car.Car;

import java.time.Instant;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.carCategory.seats >= :seats AND c IN (SELECT ct.car FROM CarTransfer ct WHERE ct.date != :date) OR c NOT IN (SELECT ct.car FROM CarTransfer ct)")
    List<Car> findAllAvailableCars(Instant date, int seats);
}