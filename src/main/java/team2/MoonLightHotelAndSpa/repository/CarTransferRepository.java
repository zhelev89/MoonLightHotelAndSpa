package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.user.User;

import java.util.List;

@Repository
public interface CarTransferRepository extends JpaRepository<CarTransfer, Long> {

    List<CarTransfer> findByCar(Car car);

    List<CarTransfer> findAllByUser(User user);
}
