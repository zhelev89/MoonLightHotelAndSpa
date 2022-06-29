package team2.MoonLightHotelAndSpa.convertors;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomResponse;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.models.rooms.Room;

@Component
public class RoomConvertor {

    public Room convert(RoomSaveRequest roomSaveRequest) {
        return Room.builder()
                .roomTitle(roomSaveRequest.getTitle())
                .image(roomSaveRequest.getImage())
                .images(roomSaveRequest.getImages())
                .description(roomSaveRequest.getDescription())
                .facilities(roomSaveRequest.getFacilities())
                .area(roomSaveRequest.getArea())
                .view(roomSaveRequest.getRoomView())
                .people(roomSaveRequest.getPeople())
                .price(roomSaveRequest.getPrice())
                .build();
    }

    public RoomResponse convert(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .title(room.getRoomTitle())
                .image(room.getImage())
                .images(room.getImages())
                .description(room.getDescription())
                .facilities(room.getFacilities())
                .view(room.getView())
                .area(room.getArea())
                .people(room.getPeople())
                .price(room.getPrice())
                .build();
    }
}
