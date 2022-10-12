package team2.MoonLightHotelAndSpa.service.car;

import team2.MoonLightHotelAndSpa.model.car.Car;

import java.time.Instant;
import java.util.List;

public interface CarService {

    Car save(Car car);

    List<Car> findAll();

    Car update(long id, Car updatedCar);

    Car findById(long id);

    void deleteById(long id);

    List<Car> findAllAvailableCars(Instant date, int seats);
}
