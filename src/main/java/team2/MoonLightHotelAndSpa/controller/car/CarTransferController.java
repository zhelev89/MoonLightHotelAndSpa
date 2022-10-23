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
import team2.MoonLightHotelAndSpa.converter.car.CarTransferConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.CarTransferUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carTransfer.SummarizeCarTransfer;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.model.car.Car;
import team2.MoonLightHotelAndSpa.model.car.CarTransfer;
import team2.MoonLightHotelAndSpa.model.user.User;
import team2.MoonLightHotelAndSpa.service.car.CarService;
import team2.MoonLightHotelAndSpa.service.car.CarTransferService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
@Tag(name = "Car Transfer")
@CrossOrigin
public class CarTransferController {

    private CarTransferConverter carTransferConverter;
    private CarTransferService carTransferService;
    private CarService carService;

    @PostMapping(value = "/{id}/transfers")
    @Operation(summary = "Save car transfer", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarTransferResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarTransferResponse> saveCarTransfer(@RequestBody CarTransferSaveRequest carTransferSaveRequest, @PathVariable long id, @AuthenticationPrincipal User user) {
        CarTransfer carTransfer = carTransferConverter.convert(carTransferSaveRequest, id, user);
        CarTransfer savedCarTransfer = carTransferService.save(carTransfer);
        CarTransferResponse carTransferResponse = carTransferConverter.convert(savedCarTransfer);
        return ResponseEntity.ok().body(carTransferResponse);
    }

    @GetMapping(value = "/{id}/transfers")
    @Operation(summary = "Find all transfers by car ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarTransferResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<List<CarTransferResponse>> findAllTransfersForCar(@PathVariable long id) {
        Car car = carService.findById(id);
        List<CarTransfer> allTransfers = carTransferService.findByCar(car.getId());
        List<CarTransferResponse> carTransferResponses = allTransfers.stream().map(carTransferConverter::convert).toList();
        return ResponseEntity.ok(carTransferResponses);
    }

    @GetMapping(value = "/{id}/transfers/{tid}")
    @Operation(summary = "Show a transfer by ID and car ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarTransferResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarTransferResponse> findByTransferIdAndCarId(@PathVariable long id, @PathVariable long tid) {
        carTransferService.carTransferIdMatch(id, tid);
        CarTransfer carTransfer = carTransferService.findById(tid);
        CarTransferResponse carTransferResponse = carTransferConverter.convert(carTransfer);
        return ResponseEntity.ok().body(carTransferResponse);
    }

    @PutMapping(value = "/{id}/transfers/{tid}")
    @Operation(summary = "Update car transfer by ID and car ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarTransferResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarTransferResponse> updateByTransferIdAndCarId(@RequestBody CarTransferUpdateRequest carTransferUpdateRequest, @PathVariable long id, @PathVariable long tid) {
        carTransferService.carTransferIdMatch(id, tid);
        CarTransfer carTransfer = carTransferConverter.convert(carTransferUpdateRequest);
        CarTransfer updatedCarTransfer = carTransferService.update(tid, carTransfer);
        CarTransferResponse carTransferResponse = carTransferConverter.convert(updatedCarTransfer);
        return ResponseEntity.ok().body(carTransferResponse);
    }

    @DeleteMapping(value = "/{id}/transfers/{tid}")
    @Operation(summary = "Delete car transfer by ID and car ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteByTransferIdAndCarId(@PathVariable long id, @PathVariable long tid) {
        carTransferService.carTransferIdMatch(id, tid);
        carTransferService.deleteById(tid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{id}/summarize")
    @Operation(summary = "Summarize car transfer", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SummarizeCarTransfer.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<SummarizeCarTransfer> summarize(@RequestBody CarTransferSaveRequest carTransferSaveRequest, @PathVariable long id, @AuthenticationPrincipal User user) {
        CarTransfer carTransfer = carTransferConverter.convert(carTransferSaveRequest, id, user);
        SummarizeCarTransfer summarizeCarTransfer = carTransferConverter.convertSummarize(carTransfer);
        return ResponseEntity.ok().body(summarizeCarTransfer);
    }
}
