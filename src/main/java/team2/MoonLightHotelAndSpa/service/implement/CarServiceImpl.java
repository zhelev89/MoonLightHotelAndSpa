package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.repository.CarRepository;
import team2.MoonLightHotelAndSpa.service.CarService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    @Transactional
    public Car update(long id, Car updatedCar) {
        Car car = findById(id);
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setImage(updatedCar.getImage());
        car.setImages(updatedCar.getImages());
        car.setYear(updatedCar.getYear());
        car.setCarCategory(updatedCar.getCarCategory());
        return car;
    }

    @Override
    public Car findById(long id) {
        return carRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Car with id:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        carRepository.deleteById(id);
    }
}
