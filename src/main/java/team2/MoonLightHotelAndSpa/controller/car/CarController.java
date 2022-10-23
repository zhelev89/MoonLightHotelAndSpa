package team2.MoonLightHotelAndSpa.controller.car;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
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
@Tag(name = "Car")
@CrossOrigin
public class CarController {

    private CarService carService;
    private CarConverter carConverter;
    private CarTransferConverter carTransferConverter;

    @PostMapping
    @Operation(summary = "Save car", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarSaveRequest carSaveRequest) {
        Car car = carConverter.convert(carSaveRequest);
        Car savedCar = carService.save(car);
        CarResponse carResponse = carConverter.convert(savedCar);
        return ResponseEntity.ok().body(carResponse);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update car by ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarResponse> updateCar(@RequestBody CarUpdateRequest carUpdateRequest, @PathVariable long id) {
        Car car = carConverter.convert(carUpdateRequest);
        Car updatedCar = carService.update(id, car);
        CarResponse carResponse = carConverter.convert(updatedCar);
        return ResponseEntity.ok().body(carResponse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete car by ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable long id) {
        carService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/available")
    @Operation(summary = "Find all available cars", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<CarResponse>> findAllAvailableCars(@RequestBody CarQueryRequest carQueryRequest) {
        Instant dateInstant = carTransferConverter.convertRequestDateToInstant(carQueryRequest.getDate());
        List<Car> findAllAvailableCars = carService.findAllAvailableCars(dateInstant, carQueryRequest.getSeats());
        return ResponseEntity.ok(findAllAvailableCars.stream().map(carConverter::convert).collect(Collectors.toList()));
    }
}
