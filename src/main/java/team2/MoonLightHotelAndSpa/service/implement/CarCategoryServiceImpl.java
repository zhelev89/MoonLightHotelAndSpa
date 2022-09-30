package team2.MoonLightHotelAndSpa.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;
import team2.MoonLightHotelAndSpa.repository.CarCategoryRepository;
import team2.MoonLightHotelAndSpa.service.CarCategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarCategoryServiceImpl implements CarCategoryService {

    private CarCategoryRepository carCategoryRepository;

    @Override
    public CarCategory save(CarCategory carCategory) {
        return carCategoryRepository.save(carCategory);
    }

    @Override
    public List<CarCategory> findAll() {
        return null;
    }

    @Override
    public CarCategory update(long id, CarCategory updatedCarCategory) {
        return null;
    }

    @Override
    public CarCategory findById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
