package team2.MoonLightHotelAndSpa.converter.room;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.model.room.Room;
import team2.MoonLightHotelAndSpa.model.room.RoomImage;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoomConverter {

    public Room convert(RoomSaveRequest roomSaveRequest) {
        return Room.builder()
                .title(roomSaveRequest.getTitle())
                .image(roomSaveRequest.getImage())
                .images(roomSaveRequest.getImages().stream()
                        .map(image -> RoomImage.builder().image(image).build())
                        .collect(Collectors.toSet()))
                .description(roomSaveRequest.getDescription())
                .facilities(roomSaveRequest.getFacilities())
                .area(roomSaveRequest.getArea())
                .people(roomSaveRequest.getPeople())
                .view(roomSaveRequest.getView())
                .price(roomSaveRequest.getPrice())
                .count(roomSaveRequest.getCount())
                .build();
    }

    public RoomResponse convert(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .image(room.getImage())
                .images(room.getImages().stream()
                        .map(RoomImage::getImage)
                        .collect(Collectors.toSet()))
                .description(room.getDescription())
                .facilities(room.getFacilities())
                .view(room.getView())
                .area(room.getArea())
                .people(room.getPeople())
                .price(room.getPrice())
                .build();
    }

    public Room convert(RoomUpdateRequest roomUpdateRequest) {
        return Room.builder()
                .title(roomUpdateRequest.getTitle())
                .image(roomUpdateRequest.getImage())
                .images(roomUpdateRequest.getImages().stream()
                        .map(image -> RoomImage.builder().image(image).build())
                        .collect(Collectors.toSet()))
                .description(roomUpdateRequest.getDescription())
                .facilities(roomUpdateRequest.getFacilities())
                .area(roomUpdateRequest.getArea())
                .price(roomUpdateRequest.getPrice())
                .people(roomUpdateRequest.getPeople())
                .price(roomUpdateRequest.getPrice())
                .view(roomUpdateRequest.getView())
                .count(roomUpdateRequest.getCount())
                .build();
    }
}
