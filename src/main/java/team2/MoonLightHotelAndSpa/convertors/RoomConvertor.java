package team2.MoonLightHotelAndSpa.convertors;

import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.dataTransferObjects.RoomSaveRequest;
import team2.MoonLightHotelAndSpa.models.rooms.Room;

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
