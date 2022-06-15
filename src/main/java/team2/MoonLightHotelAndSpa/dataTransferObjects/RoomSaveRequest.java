package team2.MoonLightHotelAndSpa.dataTransferObjects;

import lombok.Data;
import team2.MoonLightHotelAndSpa.models.rooms.RoomType;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;

@Data
public class RoomSaveRequest {

    private RoomType roomType;
    private RoomView roomView;
    private Double roomPrice;
}
