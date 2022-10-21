package team2.MoonLightHotelAndSpa.converter.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.converter.user.UserConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.SummarizeCarTransfer;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.car.CarTransferStatus;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.car.CarService;
import team2.MoonLightHotelAndSpa.service.user.UserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class CarTransferConverter {

    private final CarService carService;
    private final CarConverter carConverter;
    private final UserConverter userConverter;
    private final UserService userService;

    public CarTransfer convert(CarTransferSaveRequest carTransferSaveRequest, long carId, User user) {
        String status = String.valueOf(CarTransferStatus.UNPAID);
        Instant dateInst = convertRequestDateToInstant(carTransferSaveRequest.getDate());
        Car car = carService.findById(carId);
        return CarTransfer.builder()
                .price(car.getCarCategory().getPrice())
                .date(dateInst)
                .car(car)
                .status(status)
                .user(user)
                .build();
    }

    public CarTransferResponse convertFindAll(CarTransfer carTransfer) {
        String date = String.valueOf(carTransfer.getDate());
        return CarTransferResponse.builder()
                .id(carTransfer.getId())
                .price(carTransfer.getCar().getCarCategory().getPrice())
                .date(date)
                .car(carConverter.convert(carTransfer.getCar()))
                .user(userConverter.convert(carTransfer.getUser()))
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
        Instant dateInst = Instant.parse(carTransferUpdateRequest.getDate());
        return CarTransfer.builder()
                .date(dateInst)
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

    public Instant convertRequestDateToInstant(String date) {
        String concatenatedDate = date + " " + "00:00:00";
        return LocalDateTime
                .parse(concatenatedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
