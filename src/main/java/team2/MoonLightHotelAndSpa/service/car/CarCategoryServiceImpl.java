package team2.MoonLightHotelAndSpa.service.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team2.MoonLightHotelAndSpa.exception.RecordNotFoundException;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;
import team2.MoonLightHotelAndSpa.repository.CarCategoryRepository;

import javax.transaction.Transactional;
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
        return carCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public CarCategory update(long id, CarCategory updatedCarCategory) {
        CarCategory carCategory = findById(id);
        carCategory.setTitle(updatedCarCategory.getTitle());
        carCategory.setSeats(updatedCarCategory.getSeats());
        carCategory.setPrice(updatedCarCategory.getPrice());
        return carCategory;
    }

    @Override
    public CarCategory findById(long id) {
        return carCategoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Car category with id:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        carCategoryRepository.deleteById(id);
    }
}
