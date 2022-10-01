package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarSaveRequest;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {

    @PostMapping
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarSaveRequest carSaveRequest) {
        return null;
    }
}
