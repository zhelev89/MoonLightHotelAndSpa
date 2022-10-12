package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {
}
