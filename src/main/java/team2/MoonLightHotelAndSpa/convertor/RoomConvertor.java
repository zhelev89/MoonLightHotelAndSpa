package team2.MoonLightHotelAndSpa.convertor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.dataTransferObject.room.RoomUpdateRequest;
import team2.MoonLightHotelAndSpa.model.room.Room;

@Component
@AllArgsConstructor
public class RoomConvertor {

    public Room convert(RoomSaveRequest roomSaveRequest) {
        return Room.builder()
                .title(roomSaveRequest.getTitle())
                .image(roomSaveRequest.getImage())
                .images(roomSaveRequest.getImages())
                .description(roomSaveRequest.getDescription())
                .area(roomSaveRequest.getArea())
                .view(roomSaveRequest.getView())
                .people(roomSaveRequest.getPeople())
                .price(roomSaveRequest.getPrice())
                .count(roomSaveRequest.getCount())
                .build();
    }

    public RoomResponse convert(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .image(room.getImage())
                .images(room.getImages())
                .description(room.getDescription())
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
                .images(roomUpdateRequest.getImages())
                .description(roomUpdateRequest.getDescription())
                .area(roomUpdateRequest.getArea())
                .people(roomUpdateRequest.getPeople())
                .price(roomUpdateRequest.getPrice())
                .view(roomUpdateRequest.getView())
                .build();
    }
}
