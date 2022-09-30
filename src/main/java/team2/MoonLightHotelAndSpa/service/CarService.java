package team2.MoonLightHotelAndSpa.service;

import team2.MoonLightHotelAndSpa.model.car.Car;

import java.util.List;

public interface CarService {

    Car save(Car car);

    List<Car> findAll();

    Car update(long id, Car updatedCar);

    Car findById(long id);

    void deleteById(long id);
}
