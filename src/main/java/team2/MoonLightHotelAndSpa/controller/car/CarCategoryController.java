package team2.MoonLightHotelAndSpa.controller.car;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.car.CarCategoryConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategorySaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.carCategory.CarCategoryUpdateRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.model.car.CarCategory;
import team2.MoonLightHotelAndSpa.service.car.CarCategoryService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
@Tag(name = "Car Category")
public class CarCategoryController {

    private CarCategoryService carCategoryService;
    private CarCategoryConverter carCategoryConverter;

    @PostMapping(value = "/categories")
    @Operation(summary = "Save car category", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarCategoryResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarCategoryResponse> saveCarCategory(@RequestBody CarCategorySaveRequest carCategorySaveRequest) {
        CarCategory carCategory = carCategoryConverter.convert(carCategorySaveRequest);
        CarCategory savedCarCategory = carCategoryService.save(carCategory);
        CarCategoryResponse carCategoryResponse = carCategoryConverter.convert(savedCarCategory);
        return ResponseEntity.ok().body(carCategoryResponse);
    }

    @PutMapping(value = "/categories/{id}")
    @Operation(summary = "Update car category by ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarCategoryResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<CarCategoryResponse> updateCarCategory(@RequestBody CarCategoryUpdateRequest carCategoryUpdateRequest, @PathVariable long id) {
        CarCategory carCategory = carCategoryConverter.convert(carCategoryUpdateRequest);
        CarCategory updatedCarCategory = carCategoryService.update(id, carCategory);
        CarCategoryResponse carCategoryResponse = carCategoryConverter.convert(updatedCarCategory);
        return ResponseEntity.ok().body(carCategoryResponse);
    }

    @DeleteMapping(value = "/categories/{id}")
    @Operation(summary = "Delete car category by ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteCarCategory(@PathVariable long id) {
        carCategoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
