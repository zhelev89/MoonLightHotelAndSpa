package team2.MoonLightHotelAndSpa.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.SummarizeCarTransfer;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.CarService;
import team2.MoonLightHotelAndSpa.service.UserService;

import java.time.Instant;

@Component
@AllArgsConstructor
public class CarTransferConverter {

    private final CarService carService;
    private final CarConverter carConverter;
    private final UserConverter userConverter;
    private final UserService userService;

    public CarTransfer convert(CarTransferSaveRequest carTransferSaveRequest, long carId, User user) {
        Instant dateInst = Instant.parse(carTransferSaveRequest.getDate());
        Car car = carService.findById(carId);
        return CarTransfer.builder()
                .price(car.getCarCategory().getPrice())
                .date(dateInst)
                .car(car)
                .user(user)
                .build();
    }

    public CarTransferResponse convert(CarTransfer carTransfer) {
        String dateString = String.valueOf(carTransfer.getDate());
        String createdString = String.valueOf(carTransfer.getCreated());
        return CarTransferResponse.builder()
                .id(carTransfer.getId())
                .price(carTransfer.getPrice())
                .date(dateString)
                .created(createdString)
                .car(carConverter.convert(carTransfer.getCar()))
                .user(userConverter.convert(carTransfer.getUser()))
                .build();
    }

    public CarTransfer convert(CarTransferUpdateRequest carTransferUpdateRequest) {
//        User user = userService.findById(carTransferUpdateRequest.getUserId());
        Instant dateInst = Instant.parse(carTransferUpdateRequest.getDate());
        return CarTransfer.builder()
                .date(dateInst)
//                .user(user)
                .build();
    }

    public SummarizeCarTransfer convertSummarize(CarTransfer carTransfer) {
        String dateString = String.valueOf(carTransfer.getDate());
        return SummarizeCarTransfer.builder()
                .id(carTransfer.getId())
                .category(carTransfer.getCar().getCarCategory())
                .brand(carTransfer.getCar().getBrand())
                .model(carTransfer.getCar().getModel())
                .seats(carTransfer.getCar().getCarCategory().getSeats())
                .price(carTransfer.getCar().getCarCategory().getPrice())
                .date(dateString)
                .build();
    }
}
