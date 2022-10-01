package team2.MoonLightHotelAndSpa.converter;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategorySaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryUpdateRequest;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;

@Component
public class CarCategoryConverter {

    public CarCategory convert(CarCategorySaveRequest carCategorySaveRequest) {
        return CarCategory.builder()
                .title(carCategorySaveRequest.getCarTitle())
                .seats(carCategorySaveRequest.getSeats())
                .price(carCategorySaveRequest.getPrice())
                .build();
    }

    public CarCategoryResponse convert(CarCategory carCategory) {
        return CarCategoryResponse.builder()
                .id(carCategory.getId())
                .carTitle(carCategory.getTitle())
                .seats(carCategory.getSeats())
                .price(carCategory.getPrice())
                .build();
    }

    public CarCategory convert(CarCategoryUpdateRequest carCategoryUpdateRequest) {
        return CarCategory.builder()
                .title(carCategoryUpdateRequest.getCarTitle())
                .seats(carCategoryUpdateRequest.getSeats())
                .price(carCategoryUpdateRequest.getPrice())
                .build();
    }
}
