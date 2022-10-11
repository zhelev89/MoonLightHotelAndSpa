package team2.MoonLightHotelAndSpa.service.car;

import team2.MoonLightHotelAndSpa.model.car.CarCategory;

import java.util.List;

public interface CarCategoryService {

    CarCategory save(CarCategory carCategory);

    List<CarCategory> findAll();

    CarCategory update(long id, CarCategory updatedCarCategory);

    CarCategory findById(long id);

    void deleteById(long id);
}
