package team2.MoonLightHotelAndSpa.converter.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarUpdateRequest;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;
import team2.MoonLightHotelAndSpa.model.car.CarImage;
import team2.MoonLightHotelAndSpa.service.car.CarCategoryService;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CarConverter {

    private final CarCategoryService carCategoryService;
    private final CarCategoryConverter carCategoryConverter;

    public Car convert(CarSaveRequest carSaveRequest) {
        CarCategory carCategory = carCategoryService.findById(carSaveRequest.getCategoryId());
        return Car.builder()
                .brand(carSaveRequest.getBrand())
                .model(carSaveRequest.getModel())
                .image(carSaveRequest.getImage())
                .images(carSaveRequest.getImages().stream().map(CarImage::convert).collect(Collectors.toSet()))
                .year(carSaveRequest.getYear())
                .carCategory(carCategory)
                .build();
    }

    public CarResponse convert(Car car) {
        String createdString = String.valueOf(car.getCreated());
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .image(car.getImage())
                .images(car.getImages().stream().map(String::valueOf).collect(Collectors.toSet()))
                .year(car.getYear())
                .created(createdString)
                .category(carCategoryConverter.convert(car.getCarCategory()))
                .build();
    }

    public Car convert(CarUpdateRequest carUpdateRequest) {
        CarCategory carCategory = carCategoryService.findById(carUpdateRequest.getCategoryId());
        return Car.builder()
                .brand(carUpdateRequest.getBrand())
                .model(carUpdateRequest.getModel())
                .image(carUpdateRequest.getImage())
                .images(carUpdateRequest.getImages().stream().map(CarImage::convert).collect(Collectors.toSet()))
                .year(carUpdateRequest.getYear())
                .carCategory(carCategory)
                .build();
    }
}
