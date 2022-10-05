package team2.MoonLightHotelAndSpa.controller.room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.MoonLightHotelAndSpa.converter.room.RoomConverter;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.BadRequestMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.exceptionMessage.ResponseMessageDto;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.service.room.RoomService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rooms")
@Tag(name = "Room")
public class RoomController {

    private final RoomService roomService;
    private final RoomConverter roomConverter;

    @PostMapping
    @Operation(summary = "Save room", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    private ResponseEntity<RoomResponse> save(@RequestBody RoomSaveRequest roomSaveRequest) {
        Room room = roomConverter.convert(roomSaveRequest);
        Room savedRoom = roomService.save(room);
        RoomResponse response = roomConverter.convert(savedRoom);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find room by ID", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomResponse> findById(@PathVariable long id) {
        Room foundRoom = roomService.findById(id);
        RoomResponse roomResponse = roomConverter.convert(foundRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update room", responses = {
            @ApiResponse(description = "Successful operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomResponse.class))),
            @ApiResponse(description = "Bad request", responseCode = "400",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestMessageDto.class))),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<RoomResponse> update(@RequestBody RoomUpdateRequest roomUpdateRequest, @PathVariable long id) {
        Room convertedRoom = roomConverter.convert(roomUpdateRequest);
        Room updatedRoom = roomService.update(id, convertedRoom);
        RoomResponse roomResponse = roomConverter.convert(updatedRoom);
        return ResponseEntity.ok().body(roomResponse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete room by ID", responses = {
            @ApiResponse(description = "No content", responseCode = "204",
                    content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "Forbidden", responseCode = "403",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(description = "NotFound", responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessageDto.class)))
    })
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}