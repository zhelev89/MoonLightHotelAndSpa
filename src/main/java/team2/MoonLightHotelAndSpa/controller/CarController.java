package team2.MoonLightHotelAndSpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.car.CarCategoryConverter;
import team2.MoonLightHotelAndSpa.converter.car.CarConverter;
import team2.MoonLightHotelAndSpa.converter.car.CarTransferConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarQueryRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.car.CarUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategorySaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.SummarizeCarTransfer;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.car.CarCategoryService;
import team2.MoonLightHotelAndSpa.service.car.CarService;
import team2.MoonLightHotelAndSpa.service.car.CarTransferService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {

    private CarService carService;
    private CarConverter carConverter;
    private CarCategoryService carCategoryService;
    private CarCategoryConverter carCategoryConverter;
    private CarTransferService carTransferService;
    private CarTransferConverter carTransferConverter;

    @PostMapping
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarSaveRequest carSaveRequest) {
        Car car = carConverter.convert(carSaveRequest);
        Car savedCar = carService.save(car);
        CarResponse carResponse = carConverter.convert(savedCar);
        return ResponseEntity.ok().body(carResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarResponse> updateCar(@RequestBody CarUpdateRequest carUpdateRequest, @PathVariable long id) {
        Car car = carConverter.convert(carUpdateRequest);
        Car updatedCar = carService.update(id, car);
        CarResponse carResponse = carConverter.convert(updatedCar);
        return ResponseEntity.ok().body(carResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable long id) {
        carService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/available")
    public ResponseEntity<List<CarResponse>> findAllAvailableCars(@RequestBody CarQueryRequest carQueryRequest) {
        Instant dateInstant = carTransferConverter.convertRequestDateToInstant(carQueryRequest.getDate());
        List<Car> findAllAvailableCars = carService.findAllAvailableCars(dateInstant, carQueryRequest.getSeats());
        return ResponseEntity.ok(findAllAvailableCars.stream().map(carConverter::convert).collect(Collectors.toList()));
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<CarCategoryResponse> saveCarCategory(@RequestBody CarCategorySaveRequest carCategorySaveRequest) {
        CarCategory carCategory = carCategoryConverter.convert(carCategorySaveRequest);
        CarCategory savedCarCategory = carCategoryService.save(carCategory);
        CarCategoryResponse carCategoryResponse = carCategoryConverter.convert(savedCarCategory);
        return ResponseEntity.ok().body(carCategoryResponse);
    }

    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<CarCategoryResponse> updateCarCategory(@RequestBody CarCategoryUpdateRequest carCategoryUpdateRequest, @PathVariable long id) {
        CarCategory carCategory = carCategoryConverter.convert(carCategoryUpdateRequest);
        CarCategory updatedCarCategory = carCategoryService.update(id, carCategory);
        CarCategoryResponse carCategoryResponse = carCategoryConverter.convert(updatedCarCategory);
        return ResponseEntity.ok().body(carCategoryResponse);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCarCategory(@PathVariable long id) {
        carCategoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{id}/transfers")
    public ResponseEntity<CarTransferResponse> saveCarTransfer(@RequestBody CarTransferSaveRequest carTransferSaveRequest, @PathVariable long id, @AuthenticationPrincipal User user) {
        CarTransfer carTransfer = carTransferConverter.convert(carTransferSaveRequest, id, user);
        CarTransfer savedCarTransfer = carTransferService.save(carTransfer);
        CarTransferResponse carTransferResponse = carTransferConverter.convert(savedCarTransfer);
        return ResponseEntity.ok().body(carTransferResponse);
    }

    @GetMapping(value = "/{id}/transfers")
    public ResponseEntity<List<CarTransferResponse>> findAllTransfersForCar(@PathVariable long id) {
        Car  car = carService.findById(id);
        List<CarTransfer> allTransfers = carTransferService.findByCar(car.getId());
        List<CarTransferResponse> carTransferResponses = allTransfers.stream().map(carTransferConverter::convert).toList();
        return ResponseEntity.ok(carTransferResponses);
    }

    @GetMapping(value = "/{id}/transfers/{tid}")
    public ResponseEntity<CarTransferResponse> findByTransferIdAndCarId(@PathVariable long id, @PathVariable long tid) {
        carTransferService.carTransferIdMatch(id, tid);
        CarTransfer carTransfer = carTransferService.findById(tid);
        CarTransferResponse carTransferResponse = carTransferConverter.convert(carTransfer);
        return ResponseEntity.ok().body(carTransferResponse);
    }

    @PutMapping(value = "/{id}/transfers/{tid}")
    public ResponseEntity<CarTransferResponse> updateByTransferIdAndCarId(@RequestBody CarTransferUpdateRequest carTransferUpdateRequest, @PathVariable long id, @PathVariable long tid) {
        carTransferService.carTransferIdMatch(id, tid);
        CarTransfer carTransfer = carTransferConverter.convert(carTransferUpdateRequest);
        CarTransfer updatedCarTransfer = carTransferService.update(tid, carTransfer);
        CarTransferResponse carTransferResponse = carTransferConverter.convert(updatedCarTransfer);
        return ResponseEntity.ok().body(carTransferResponse);
    }

    @DeleteMapping(value = "/{id}/transfers/{tid}")
    public ResponseEntity<HttpStatus> deleteByTransferIdAndCarId(@PathVariable long id, @PathVariable long tid) {
        carTransferService.carTransferIdMatch(id, tid);
        carTransferService.deleteById(tid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{id}/summarize")
    public ResponseEntity<SummarizeCarTransfer> summarize(@RequestBody CarTransferSaveRequest carTransferSaveRequest, @PathVariable long id, @AuthenticationPrincipal User user) {
        CarTransfer carTransfer = carTransferConverter.convert(carTransferSaveRequest, id, user);
        SummarizeCarTransfer summarizeCarTransfer = carTransferConverter.convertSummarize(carTransfer);
        return ResponseEntity.ok().body(summarizeCarTransfer);
    }
}
