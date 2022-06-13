package team2.MoonLight.Hotel.and.Spa.convertors;

import org.springframework.stereotype.Component;
import team2.MoonLight.Hotel.and.Spa.dataTransferObjects.RoomSaveRequest;
import team2.MoonLight.Hotel.and.Spa.models.rooms.Room;

@Component
public class RoomConvertor {

    public Room convert(RoomSaveRequest roomSaveRequest) {
        return Room.builder()
                .roomType(roomSaveRequest.getRoomType())
                .roomView(roomSaveRequest.getRoomView())
                .roomPrice(roomSaveRequest.getRoomPrice())
                .build();
    }
}
